package com.luter.heimdall.admin.module.sys.service.impl;


import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.service.SysOnlineUserService;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.session.Page;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysOnlineUserServiceImpl extends BaseServiceImpl implements SysOnlineUserService {

    private final AuthenticationManager authenticationManager;

    @Override
    public Page<SimpleSession> getOnlineUser(PagerVO page) {
        return authenticationManager.getActiveSessions(page.getPage(), page.getSize());
    }

    @Override
    public Boolean kickoutBySessionId(String sessionId) {
        return authenticationManager.kickOutSession(sessionId);
    }

    @Override
    public Boolean kickoutByPrincipal(String principal) {
        return authenticationManager.kickOutPrincipal(principal);
    }
}
