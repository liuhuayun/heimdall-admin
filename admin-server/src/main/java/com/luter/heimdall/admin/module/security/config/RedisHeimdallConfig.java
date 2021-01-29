package com.luter.heimdall.admin.module.security.config;

import com.luter.heimdall.cache.redis.authorization.RedisAuthorizationMetaDataDao;
import com.luter.heimdall.cache.redis.limiter.RedisLoginPasswordRetryLimitImpl;
import com.luter.heimdall.cache.redis.session.RedisSessionDaoImpl;
import com.luter.heimdall.core.authorization.authority.GrantedAuthority;
import com.luter.heimdall.core.authorization.dao.AuthorizationMetaDataCacheDao;
import com.luter.heimdall.core.authorization.service.AuthorizationMetaDataService;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.manager.AuthorizationManager;
import com.luter.heimdall.core.manager.limiter.LoginPasswordRetryLimit;
import com.luter.heimdall.core.servlet.ServletHolder;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.core.session.dao.SessionDAO;
import com.luter.heimdall.core.session.scheduler.DefaultInvalidSessionClearScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RedisHeimdallConfig {
    private final RedisTemplate<String, SimpleSession> sessionRedisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, Collection<String>> sysAuthRedisTemplate;
    private final RedisTemplate<String, List<? extends GrantedAuthority>> userAuthRedisTemplate;

    @Bean
    public LoginPasswordRetryLimit loginPasswordRetryLimit() {
        final RedisLoginPasswordRetryLimitImpl loginPasswordRetryLimit = new RedisLoginPasswordRetryLimitImpl(stringRedisTemplate);
        loginPasswordRetryLimit.setAttemptLimit(5);
        loginPasswordRetryLimit.setLockedDuration(60);
        return loginPasswordRetryLimit;
    }

    @Bean
    public SessionDAO sessionDAO(ServletHolder servletHolder) {
        log.warn("初始化 SessionDAO");
        return new RedisSessionDaoImpl(sessionRedisTemplate, stringRedisTemplate, userAuthRedisTemplate, servletHolder);
    }

    @Bean
    public AuthenticationManager authenticationManager(SessionDAO sessionDAO) {
        log.warn("初始化 认证管理器");
        return new AuthenticationManager(sessionDAO);
    }

    @Bean
    public AuthorizationMetaDataCacheDao authorizationMetaDataCacheDao() {
        log.warn("初始化 系统权限数据 MetaDataDao");
        return new RedisAuthorizationMetaDataDao(sysAuthRedisTemplate);
    }

    @Bean
    public AuthorizationManager authorizationManager(AuthenticationManager authenticationManager
            , AuthorizationMetaDataService authorizationMetaDataService, AuthorizationMetaDataCacheDao authorizationMetaDataCacheDao) {
        log.warn("初始化 授权管理器");
        return new AuthorizationManager(authorizationMetaDataService,
                authorizationMetaDataCacheDao, authenticationManager);
    }

    @Bean
    public DefaultInvalidSessionClearScheduler defaultInvalidSessionClearScheduler(SessionDAO sessionDAO) {
        log.warn("初始化 Session 自动清理任务");
        return new DefaultInvalidSessionClearScheduler(sessionDAO);
    }
}
