package com.luter.heimdall.admin.base.service.impl;


import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.starter.jpa.base.service.AuditorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JpaAuditorServiceImpl extends BaseServiceImpl implements AuditorService {
    @Override
    public Long getCurrentUserId() {
        final SysUserDTO currentUser = getCurrentUserDTO(false, false);
        if (null != currentUser) {
            log.debug("JPA审计，获取当前登录用户ID：{}", currentUser.getId());
            return currentUser.getId();
        }
        return null;
    }
}
