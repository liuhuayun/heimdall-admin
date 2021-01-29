package com.luter.heimdall.admin.module.security.service;

import cn.hutool.core.util.StrUtil;
import com.luter.heimdall.admin.base.enums.SysResourceTypeEnum;
import com.luter.heimdall.admin.module.security.details.AppUserDetails;
import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysResourceEntity;
import com.luter.heimdall.admin.module.sys.entity.SysRoleEntity;
import com.luter.heimdall.admin.module.sys.entity.SysUserEntity;
import com.luter.heimdall.admin.module.sys.repository.SysResourceRepository;
import com.luter.heimdall.admin.module.sys.repository.SysUserRepository;
import com.luter.heimdall.core.authorization.authority.GrantedAuthority;
import com.luter.heimdall.core.authorization.authority.SimpleGrantedAuthority;
import com.luter.heimdall.core.authorization.service.AuthorizationMetaDataService;
import com.luter.heimdall.core.exception.AccountException;
import com.luter.heimdall.core.session.SimpleSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorizationMetaDataServiceImpl implements AuthorizationMetaDataService {
    private final SysResourceRepository sysResourceRepository;
    private final SysUserRepository sysUserRepository;

    @Override
    public Map<String, Collection<String>> loadSysAuthorities() {
        final List<SysResourceEntity> objects = sysResourceRepository.findSysResourceEntitiesByResType(SysResourceTypeEnum.PERM.value());
        if (null != objects && !objects.isEmpty()) {
            Map<String, Collection<String>> perms = new LinkedHashMap<>();
            for (SysResourceEntity resource : objects) {
                if (StrUtil.isNotBlank(resource.getUri()) && StrUtil.isNotBlank(resource.getPerm())) {
                    perms.put(resource.getUri(), Collections.singletonList(resource.getPerm()));
                }

            }
            return perms;
        }
        return new LinkedHashMap<>();
    }

    @Override
    public List<? extends GrantedAuthority> loadUserAuthorities(SimpleSession session) {
        final AppUserDetails details = (AppUserDetails) session.getDetails();
        final SysUserDTO user = details.getUser();
        final Optional<SysUserEntity> byId = sysUserRepository.findById(user.getId());
        if (!byId.isPresent()) {
            throw new AccountException("获取用户信息失败");
        }
        final Set<SysRoleEntity> roles = byId.get().getRoles();
        //通过用户角色获取用户权限，去重
        if (null != roles && !roles.isEmpty()) {
            List<SysResourceEntity> all = new ArrayList<>();
            for (SysRoleEntity role : roles) {
                if (null != role.getResources() && !role.getResources().isEmpty()) {
                    all.addAll(role.getResources());
                }
            }
            final ArrayList<SysResourceEntity> userResources = all.stream().collect(collectingAndThen(toCollection(()
                    -> new TreeSet<>(comparing(SysResourceEntity::getId))), ArrayList::new));
            return userResources.stream().map(d -> new SimpleGrantedAuthority(d.getPerm())).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
