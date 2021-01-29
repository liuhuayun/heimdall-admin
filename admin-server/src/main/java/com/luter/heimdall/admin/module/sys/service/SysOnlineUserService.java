package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.core.session.Page;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.util.Collection;

public interface SysOnlineUserService extends BaseService {


    Collection<SimpleSession> getOnlineUser();


    Boolean kickoutBySessionId(String sessionId);

    Boolean kickoutByPrincipal(String principal);

}
