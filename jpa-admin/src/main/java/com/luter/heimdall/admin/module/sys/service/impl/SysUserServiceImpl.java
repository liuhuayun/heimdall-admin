package com.luter.heimdall.admin.module.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.security.details.AppUserDetails;
import com.luter.heimdall.admin.module.security.encoder.PasswordEncoder;
import com.luter.heimdall.admin.module.sys.dto.SysPostDTO;
import com.luter.heimdall.admin.module.sys.dto.SysResourceDTO;
import com.luter.heimdall.admin.module.sys.dto.SysRoleDTO;
import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.admin.module.sys.entity.*;
import com.luter.heimdall.admin.module.sys.mapper.SysPostMapper;
import com.luter.heimdall.admin.module.sys.mapper.SysResourceMapper;
import com.luter.heimdall.admin.module.sys.mapper.SysRoleMapper;
import com.luter.heimdall.admin.module.sys.mapper.SysUserMapper;
import com.luter.heimdall.admin.module.sys.repository.SysResourceRepository;
import com.luter.heimdall.admin.module.sys.repository.SysUserRepository;
import com.luter.heimdall.admin.module.sys.service.SysDepartmentService;
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
import com.luter.heimdall.starter.utils.context.BaseContextHolder;
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
import java.util.stream.Collectors;

/**
 * 系统用户 服务实现
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl implements SysUserService {

    private final SysUserRepository repository;
    private final SysUserMapper mapper;

    private final SysResourceRepository sysResourceRepository;

    private final SysResourceMapper sysResourceMapper;


    private final SysPostMapper sysPostMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysDepartmentService sysDepartmentService;

    private final CaptchaConfig captchaConfig;
    private final CaptchaService captchaService;
    private final LoginPasswordRetryLimit retryLimit;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public PageDTO<SysUserDTO> list(SysUserVO param, PagerVO pagerVO) {
        Page<SysUserEntity> page = repository.findAll((Specification<SysUserEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //            if (null != param.getId()) {
            //                predicates.add(criteriaBuilder.equal(root.get(SysUserEntity_.ID), param.getId()));
            //            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, getPager(pagerVO));
        return toPageData(page.map(mapper::toDto));
    }

    @Override
    public SysUserDTO getById(Long id) {
        final SysUserDTO user = mapper.toDto(getEntityById(SysUserEntity.class, id));
        user.setRoles(getUserRoles(user.getId()));
        user.setDepartment(sysDepartmentService.getById(user.getDepartmentId()));
        user.setPosts(getUserPosts(user.getId()));
        return user;
    }

    @Override
    public SysUserDTO save(SysUserVO param) {
        SysUserEntity toSave = new SysUserEntity();
        //此处应该按需get set 数据
        //toSave.setXXX(param.getXXX());
        BeanUtil.copyProperties(param, toSave);
        final SysUserEntity entity = repository.saveAndFlush(toSave);
        return mapper.toDto(entity);
    }

    @Override
    public List<SysResourceDTO> loadUserMenusByUserId(Long id) {
        return sysResourceMapper.entityListToDTOList(sysResourceRepository.loadUserMenusByUserId(id));
    }

    @Override
    public SysResourceDTO getUserMenuTree(Long id) {
        List<SysResourceDTO> resources = this.loadUserMenusByUserId(id);
        if (null != resources && !resources.isEmpty()) {
            SysResourceDTO root = new SysResourceDTO();
            root.setId(0L);
            root.setName("RootNode");
            root.setTitle("根节点");
            root.setEnabled(true);
            root.setHidden(true);
            //排个序
            //找子节点
            for (SysResourceDTO l : resources) {
                List<SysResourceDTO> children = resources.stream()
                        .filter(f -> l.getId().equals(f.getPid())).collect(Collectors.toList());
                l.setChildren(children);
            }
            //确定根节点
            return root.setChildren(resources.stream()
                    .filter(f -> null != f.getPid() && f.getPid().equals(root.getId())).collect(Collectors.toList()));
        }
        return null;
    }

    @Override
    public List<SysRoleDTO> getUserRoles(Long id) {
        String sql = "SELECT   " +
                "    m_role.*  " +
                "FROM  " +
                "    m_role_user  " +
                "        LEFT JOIN  " +
                "    m_role ON m_role_user.role_id = m_role.id  " +
                "        LEFT JOIN  " +
                "    m_user ON m_role_user.user_id = m_user.id  " +
                "WHERE  " +
                "    m_user.id = ?0";

        return sysRoleMapper.entityListToDTOList(listEntitiesBySql(SysRoleEntity.class, sql, id));
    }


    @Override
    public List<SysPostDTO> getUserPosts(Long id) {
        String sql = "SELECT   " +
                "    m_post.*  " +
                "FROM  " +
                "    m_post_user  " +
                "        LEFT JOIN  " +
                "    m_post ON m_post.id = m_post_user.post_id  " +
                "WHERE  " +
                "   m_post_user.user_id = ?0";
        return sysPostMapper.entityListToDTOList(listEntitiesBySql(SysPostEntity.class, sql, id));
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
    public void update(SysUserVO param) {
        SysUserEntity toUpdate = getEntityById(SysUserEntity.class, param.getId());
        toUpdate.setNickName(param.getNickName());
        toUpdate.setRealName(param.getRealName());
        toUpdate.setRemarks(param.getRemarks());
        toUpdate.setCellPhone(param.getCellPhone());
        toUpdate.setGender(param.getGender());
        if (null != param.getRoles() && !param.getRoles().isEmpty()) {
            deleteByProperty(SysRoleUserEntity.class, "userId", toUpdate.getId());
            final List<SysRoleUserEntity> collect = param.getRoles().stream().map(d -> {
                SysRoleUserEntity roleUserEntity = new SysRoleUserEntity();
                roleUserEntity.setRoleId(d.getId());
                roleUserEntity.setUserId(toUpdate.getId());
                return roleUserEntity;
            }).collect(Collectors.toList());
            saveBatchEntity(collect);
            authenticationManager.getSessionDAO().clearAllUserAuthorities();
        }
        if (null != param.getPosts() && !param.getPosts().isEmpty()) {
            deleteByProperty(SysPostUserEntity.class, "userId", toUpdate.getId());
            final List<SysPostUserEntity> posts = param.getPosts().stream().map(d -> {
                SysPostUserEntity entity = new SysPostUserEntity();
                entity.setPostId(d.getId());
                entity.setUserId(toUpdate.getId());
                return entity;
            }).collect(Collectors.toList());
            saveBatchEntity(posts);
        }
        if (null != param.getDepartment()) {
            toUpdate.setDepartmentId(param.getDepartment().getId());
        }
        updateEntity(toUpdate);
    }

    @Override
    public int deleteById(Long id) {
        if (null == id) {
            throw new LuterIllegalParameterException("ID不能为空");
        }
        return deleteEntityById(SysUserEntity.class, id);
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
        BaseContextHolder.setUserId(userDTO.getId());
        return session.getId();
    }

    @Override
    public void logout() {
        authenticationManager.logout();
        BaseContextHolder.setUserId(null);
    }
}
