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


import com.luter.heimdall.starter.captcha.dto.CaptchaDTO;
import com.luter.heimdall.starter.captcha.service.CaptchaService;
import com.luter.heimdall.starter.jpa.base.controller.BaseJpaController;
import com.luter.heimdall.starter.model.base.ResponseVO;
import com.luter.heimdall.starter.syslog.annotation.SysLog;
import com.luter.heimdall.starter.utils.response.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "图形验证码", tags = "图形验证码")
@RestController
@RequestMapping("/sys/captcha")
@AllArgsConstructor
public class SysCaptchaController extends BaseJpaController {
    private final CaptchaService captchaService;

    @GetMapping
    @ApiOperation(value = "获取验证码", notes = "获取验证码", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<CaptchaDTO>> genCaptcha() {
        return ResponseUtils.ok(captchaService.genCaptcha());
    }


    @PostMapping
    @ApiOperation(value = "验证码校验", notes = "验证码校验", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Void>> checkCaptcha(String uuid, String code) {
        if (captchaService.checkCaptcha(uuid, code)) {
            return ResponseUtils.ok();
        }
        return ResponseUtils.fail("验证码错误");
    }
}
