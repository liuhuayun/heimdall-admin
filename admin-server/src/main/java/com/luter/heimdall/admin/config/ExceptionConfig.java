package com.luter.heimdall.admin.config;

import com.luter.heimdall.core.exception.UnAuthorizedException;
import com.luter.heimdall.core.exception.UnAuthticatedException;
import com.luter.heimdall.starter.jpa.exception.AbstractJpaGlobalExceptionAdvise;
import com.luter.heimdall.starter.model.base.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class ExceptionConfig extends AbstractJpaGlobalExceptionAdvise {

    @ExceptionHandler(value = {UnAuthticatedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object exception(HttpServletRequest request, UnAuthticatedException e) {
        String message = "请登录后再操作", error = e.getMessage();
        log.error(LOG_FORMAT, e.getClass().getName(), message, error, HttpStatus.UNAUTHORIZED);
        return dealError(request, ResponseVO.fail(HttpStatus.UNAUTHORIZED, message, error));
    }

    @ExceptionHandler(value = {UnAuthorizedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Object exception(HttpServletRequest request, UnAuthorizedException e) {
        String message = "不具备操作权限", error = e.getMessage();
        log.error(LOG_FORMAT, e.getClass().getName(), message, error, HttpStatus.FORBIDDEN);
        return dealError(request, ResponseVO.fail(HttpStatus.FORBIDDEN, message, error));
    }
}
