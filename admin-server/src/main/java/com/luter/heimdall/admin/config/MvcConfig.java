package com.luter.heimdall.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.luter.heimdall.boot.starter.interceptor.PermBasedAuthorizeInterceptor;
import com.luter.heimdall.boot.starter.resolver.CurrentUserRequestArgumentResolver;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.manager.AuthorizationManager;
import com.luter.heimdall.starter.boot.config.DefaultWebMvcConfig;
import com.luter.heimdall.starter.utils.json.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MvcConfig extends DefaultWebMvcConfig {
    private final AuthorizationManager authorizationManager;
    private final AuthenticationManager authenticationManager;

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
        //注入用户detail信息
        log.warn("注入header User信息拦截器");
        argumentResolvers.add(new CurrentUserRequestArgumentResolver(authenticationManager));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截处理操作的匹配路径
        //放开静态拦截
        log.warn("初始化 Url 权限拦截器");
        registry.addInterceptor(new PermBasedAuthorizeInterceptor(authorizationManager))
                //拦截所有路径
                .addPathPatterns("/**")
                //排除路径
                //排除静态资源拦截
                .excludePathPatterns(
                        "/login/**",
                        "/logout/**",
                        "/current/**",
                        "/static/**",
                        "/resources/**",
                        "/webjars/**",
                        "/error/**");

    }
}
