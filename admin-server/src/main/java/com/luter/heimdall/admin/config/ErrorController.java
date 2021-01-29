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

package com.luter.heimdall.admin.config;

import com.luter.heimdall.core.exception.UnAuthorizedException;
import com.luter.heimdall.core.exception.UnAuthticatedException;
import com.luter.heimdall.starter.boot.controller.BaseServletErrorController;
import com.luter.heimdall.starter.jpa.exception.AbstractJpaGlobalExceptionAdvise;
import com.luter.heimdall.starter.model.base.ResponseVO;
import com.luter.heimdall.starter.utils.json.JacksonUtils;
import com.luter.heimdall.starter.utils.response.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
@Slf4j
public class ErrorController extends BaseServletErrorController {
    public ErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties, errorViewResolvers);
    }
    @Override
    public ResponseVO<Void> getError(HttpServletRequest request) {
        final ErrorAttributeOptions of = ErrorAttributeOptions.of(
                ErrorAttributeOptions.Include.BINDING_ERRORS,
                ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.STACK_TRACE);
        Map<String, Object> body = getErrorAttributes(request, of);
        HttpStatus status = super.getStatus(request);
        final Object exceptionObject = body.get("exception");
        if (null != exceptionObject) {
            String exception = body.get("exception").toString();
            if (UnAuthticatedException.class.getName().equals(exception)) {
                status = HttpStatus.UNAUTHORIZED;
            }
            if (UnAuthorizedException.class.getName().equals(exception)) {
                status = HttpStatus.FORBIDDEN;
            }
        }
        String msg = body.get("error").toString();
        switch (status.value()) {
            case 401:
                msg = "请登录后操作";
                break;
            case 403:
                msg = "您不具备访问此资源的权限";
                break;
            case 404:
                msg = "您要访问的资源不存在";
                break;
            case 405:
                msg = "请求方法错误";
                break;
            default:
                break;
        }
        String res = body.get("path").toString();
        boolean isJson = ResponseUtils.isJson(request);
        //输出自定义的Json格式
        final ResponseVO<Void> error = ResponseVO.fail(status, msg, "Basic Request Error :  " + body.get("message").toString());
        log.error("===系统错误====: 资源: {}, JSON请求 : {}, Error :{} ", res, isJson, JacksonUtils.toJson(error));
        return error;
    }
}
