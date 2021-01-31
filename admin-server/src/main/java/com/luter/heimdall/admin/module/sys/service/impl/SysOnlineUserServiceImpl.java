/*
 *
 *  *
 *  *
 *  *      Copyright 2020-2021 Luter.me
 *  *
 *  *      Licensed under the Apache License, Version 2.0 (the "License");
 *  *      you may not use this file except in compliance with the License.
 *  *      You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *      Unless required by applicable law or agreed to in writing, software
 *  *      distributed under the License is distributed on an "AS IS" BASIS,
 *  *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *      See the License for the specific language governing permissions and
 *  *      limitations under the License.
 *  *
 *  *
 *
 */

package com.luter.heimdall.admin.module.sys.service.impl;


import com.luter.heimdall.admin.base.service.impl.BaseServiceImpl;
import com.luter.heimdall.admin.module.sys.service.SysOnlineUserService;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.session.Page;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysOnlineUserServiceImpl extends BaseServiceImpl implements SysOnlineUserService {

    private final AuthenticationManager authenticationManager;

    @Override
    public PageDTO<SimpleSession> getOnlineUser(PagerVO page) {
        final Page<SimpleSession> activeSessions = authenticationManager.getActiveSessions(page.getPage(), page.getSize());
        final List<SimpleSession> collect = activeSessions.getRecords().stream().filter(d -> null != d.getDetails()).collect(Collectors.toList());
        return new PageDTO<>(page.getPage(), page.getSize(), activeSessions.getTotalCount(), collect);
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
