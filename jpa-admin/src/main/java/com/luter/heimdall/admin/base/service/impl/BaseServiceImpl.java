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


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.security.details.AppUserDetails;
import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.admin.module.sys.entity.SysUserEntity;
import com.luter.heimdall.admin.module.sys.mapper.SysUserMapper;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.starter.jpa.base.service.impl.BaseJpaServiceImpl;
import com.luter.heimdall.starter.utils.exception.LuterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public abstract class BaseServiceImpl extends BaseJpaServiceImpl implements BaseService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public SysUserDTO getCurrentUserDTO(boolean isThrowEx, boolean fromDb) {
        final SimpleSession currentUser = authenticationManager.getCurrentUser(isThrowEx);
        if (null != currentUser && null != currentUser.getDetails()) {
            AppUserDetails userDetails = (AppUserDetails) currentUser.getDetails();
            if (null != userDetails) {
                if (fromDb) {
                    return sysUserMapper.toDto(getCurrentUserEntity(isThrowEx));
                } else {
                    return userDetails.getUser();
                }
            }

        }

        if (isThrowEx) {
            throw new LuterException("获取当前登录用户信息失败");
        } else {
            return null;
        }

    }

    @Override
    public SysUserEntity getCurrentUserEntity(boolean isThrowEx) {
        final SimpleSession currentUser = authenticationManager.getCurrentUser(isThrowEx);
        if (null != currentUser && null != currentUser.getDetails()) {
            AppUserDetails userDetails = (AppUserDetails) currentUser.getDetails();
            if (null != userDetails) {
                final SysUserEntity entityById = getEntityById(SysUserEntity.class, userDetails.getUser().getId());
                if (null != entityById) {
                    return entityById;
                }
            }

        }
        if (isThrowEx) {
            throw new LuterException("获取当前登录用户信息失败");
        } else {
            return null;
        }
    }

}
