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

package com.luter.heimdall.admin.module.sys.controller;


import com.luter.heimdall.admin.module.sys.service.SysOnlineUserService;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.starter.jpa.base.controller.BaseJpaController;
import com.luter.heimdall.starter.model.base.ResponseVO;
import com.luter.heimdall.starter.syslog.annotation.SysLog;
import com.luter.heimdall.starter.utils.response.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Slf4j
@RestController
@RequestMapping("/sys/online")
@Api(value = "在线用户管理", tags = "在线用户管理")
@RequiredArgsConstructor
public class SysOnlineUserController extends BaseJpaController {

    private final SysOnlineUserService sysOnlineUserService;


    @GetMapping("/list")
    @ApiOperation(value = "获取在线用户列表", notes = "获取在线用户列表", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Collection<SimpleSession>>> list() {
        return ResponseUtils.ok(sysOnlineUserService.getOnlineUser());
    }

    @PostMapping("/kick")
    @ApiOperation(value = "强制注销用户", notes = "强制注销用户", response = ResponseVO.class)
    @SysLog
    public Object kickOut(@RequestParam String sessionId) {
        return ResponseUtils.ok(sysOnlineUserService.kickoutBySessionId(sessionId));
    }
}
