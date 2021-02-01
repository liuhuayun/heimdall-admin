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

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.luter.heimdall.boot.starter.interceptor.PermBasedAuthorizeInterceptor;
import com.luter.heimdall.boot.starter.resolver.CurrentUserRequestArgumentResolver;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.manager.AuthorizationManager;
import com.luter.heimdall.starter.boot.config.DefaultWebMvcConfig;
import com.luter.heimdall.starter.oss.config.OSSConfig;
import com.luter.heimdall.starter.utils.json.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MvcConfig extends DefaultWebMvcConfig {
    private final AuthorizationManager authorizationManager;
    private final AuthenticationManager authenticationManager;
    private final OSSConfig config;

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return JacksonUtils.getObjectMapper();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = converter.getObjectMapper();
        mapper.registerModule(hibernate5Module());
        return converter;
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        Hibernate5Module module = new Hibernate5Module();
        module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        module.enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        return module;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        log.warn("注入header User信息拦截器");
        argumentResolvers.add(new CurrentUserRequestArgumentResolver(authenticationManager));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        log.warn("初始化 Url 权限拦截器");
        registry.addInterceptor(new PermBasedAuthorizeInterceptor(authorizationManager))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login/**",
                        "/logout/**",
                        "/current/**",
                        "/static/**",
                        "/resources/**",
                        "/webjars/**",
                        "/error/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (StrUtil.isBlank(config.getLocal().getUrlPrefix()) || StrUtil.isBlank(config.getLocal().getSavePath())) {
            throw new RuntimeException("文件上传参数配置错误,url-prefix或者path错误，上传功能将会不可用");
        }
        String savePath = config.getLocal().getSavePath().endsWith("/")
                ? config.getLocal().getSavePath() : config.getLocal().getSavePath() + "/";
        String urlPrefix = config.getLocal().getUrlPrefix().startsWith("/")
                ? config.getLocal().getUrlPrefix() : config.getLocal().getUrlPrefix();
        log.debug("初始化本地资源映射,url:{},location:{}", urlPrefix + "/**", savePath);
        registry.addResourceHandler(urlPrefix + "/**").addResourceLocations("file:" + savePath);
    }

}
