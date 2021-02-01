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
