package com.luter.heimdall.admin.module.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.security.details.AppUserDetails;
import com.luter.heimdall.admin.module.security.encoder.PasswordEncoder;
import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysUserEntity;
import com.luter.heimdall.admin.module.sys.entity.SysUserEntity_;
import com.luter.heimdall.admin.module.sys.mapper.SysDepartmentMapper;
import com.luter.heimdall.admin.module.sys.mapper.SysPostMapper;
import com.luter.heimdall.admin.module.sys.mapper.SysRoleMapper;
import com.luter.heimdall.admin.module.sys.mapper.SysUserMapper;
import com.luter.heimdall.admin.module.sys.repository.SysUserRepository;
import com.luter.heimdall.admin.module.sys.service.SysUserService;
import com.luter.heimdall.admin.module.sys.vo.SysUserVO;
import com.luter.heimdall.core.exception.AccountException;
import com.luter.heimdall.core.exception.HeimdallException;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.manager.limiter.LoginPasswordRetryLimit;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.starter.captcha.config.CaptchaConfig;
import com.luter.heimdall.starter.captcha.service.CaptchaService;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.utils.exception.LuterIllegalParameterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl implements SysUserService {
    private final SysUserRepository repository;
    private final SysUserMapper mapper;
    private final SysRoleMapper roleMapper;
    private final SysPostMapper postMapper;
    private final SysDepartmentMapper departmentMapper;
    private final CaptchaConfig captchaConfig;
    private final CaptchaService captchaService;
    private final LoginPasswordRetryLimit retryLimit;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public PageDTO<SysUserDTO> list(SysUserVO param, PagerVO pagerVO) {
        Page<SysUserEntity> page = repository.findAll((Specification<SysUserEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StrUtil.isNotEmpty(param.getUsername())) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get(SysUserEntity_.USERNAME), "%" + param.getUsername() + "%"),
                        criteriaBuilder.like(root.get(SysUserEntity_.NICK_NAME), "%" + param.getUsername() + "%"),
                        criteriaBuilder.like(root.get(SysUserEntity_.REAL_NAME), "%" + param.getUsername() + "%")
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page, SysUserDTO.class);
    }

    @Override
    public SysUserDTO getById(Long id) {
        return mapper.toDto(getEntityById(SysUserEntity.class, id));
    }


    @Override
    public SysUserDTO save(SysUserVO param) {
        SysUserEntity toSave = mapper.voToEntity(param);
        toSave.setPassword(passwordEncoder.encode(toSave.getPassword()));
        if (null != param.getUserRoles() && !param.getUserRoles().isEmpty()) {
            toSave.setRoles(Sets.newHashSet(roleMapper.voListToEntityList(param.getUserRoles())));
        }
        if (null != param.getPosts() && !param.getPosts().isEmpty()) {
            toSave.setPosts(Sets.newHashSet(postMapper.voListToEntityList(param.getPosts())));
        }
        if (null != param.getDepartment()) {
            toSave.setDepartment(departmentMapper.voToEntity(param.getDepartment()));
        }
        final SysUserEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override

    public void update(SysUserVO param) {
        SysUserEntity toUpdate = getEntityById(SysUserEntity.class, param.getId());
        toUpdate.setNickName(param.getNickName());
        toUpdate.setRealName(param.getRealName());
        toUpdate.setRemarks(param.getRemarks());
        toUpdate.setCellPhone(param.getCellPhone());
        toUpdate.setGender(param.getGender());
        if (null != param.getUserRoles() && !param.getUserRoles().isEmpty()) {
            toUpdate.setRoles(Sets.newHashSet(roleMapper.voListToEntityList(param.getUserRoles())));
            authenticationManager.getSessionDAO().clearAllUserAuthorities();
        }
        if (null != param.getPosts() && !param.getPosts().isEmpty()) {
            toUpdate.setPosts(Sets.newHashSet(postMapper.voListToEntityList(param.getPosts())));
        }
        if (null != param.getDepartment()) {
            toUpdate.setDepartment(departmentMapper.voToEntity(param.getDepartment()));
        }
        updateEntity(toUpdate);
    }

    @Override
    public void updateUserInfo(SysUserEntity user) {
        updateEntity(user);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysUserEntity.class, id);
    }

    @Override
    public void resetPassword(SysUserVO param) {
        if (StrUtil.isEmpty(param.getPassword())) {
            throw new LuterIllegalParameterException("请输入密码");
        }
        SysUserEntity user = getEntityById(SysUserEntity.class, param.getId());
        user.setPassword(passwordEncoder.encode(param.getPassword()));
        updateEntity(user);
    }

    @Override
    public Serializable login(SysUserVO user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            throw new AccountException("用户名或者密码不能为空,请输入");
        }
        final SysUserEntity sysUserEntityByUsername = repository.findSysUserEntityByUsername(user.getUsername());
        if (null == sysUserEntityByUsername) {
            throw new AccountException("用户名密码错误:Non");
        }
        if (sysUserEntityByUsername.getLocked()) {
            throw new HeimdallException("账号被锁定,请联系管理员处理");
        }
        if (captchaConfig.isEnabled()) {
            if (!captchaService.checkCaptcha(user.getUuid(), user.getCaptcha())) {
                throw new HeimdallException("验证码错误");
            }
        }
        if (!passwordEncoder.matches(user.getPassword(), sysUserEntityByUsername.getPassword())) {
            retryLimit.increase(sysUserEntityByUsername.getUsername());
            final int leftCount = retryLimit.leftCount(sysUserEntityByUsername.getUsername());
            if (leftCount == 0) {
                throw new HeimdallException("用户名密码错误,你已经剩下最后一次机会了,千万输入正确啊....");
            }
            throw new HeimdallException("用户名密码错误");
        }
        final SysUserDTO userDTO = mapper.toDto(sysUserEntityByUsername);
        AppUserDetails userDetails = new AppUserDetails(userDTO);
        final SimpleSession session = authenticationManager.login(userDetails);
        retryLimit.remove(sysUserEntityByUsername.getUsername());
        return session.getId();
    }

    @Override
    public void logout() {
        authenticationManager.logout();
    }
}
