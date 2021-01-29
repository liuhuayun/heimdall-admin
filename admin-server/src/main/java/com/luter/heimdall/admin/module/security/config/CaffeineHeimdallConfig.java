//package com.luter.heimdall.admin.module.security;
//
//import com.luter.heimdall.cache.caffeine.CaffeineAuthorizationMetaDataDao;
//import com.luter.heimdall.cache.caffeine.CaffeineLoginPasswordRetryLimitImpl;
//import com.luter.heimdall.cache.caffeine.CaffeineSessionDaoImpl;
//import com.luter.heimdall.core.authorization.dao.AuthorizationMetaDataCacheDao;
//import com.luter.heimdall.core.authorization.service.AuthorizationMetaDataService;
//import com.luter.heimdall.core.manager.AuthenticationManager;
//import com.luter.heimdall.core.manager.AuthorizationManager;
//import com.luter.heimdall.core.manager.limiter.LoginPasswordRetryLimit;
//import com.luter.heimdall.core.servlet.ServletHolder;
//import com.luter.heimdall.core.session.dao.SessionDAO;
//import com.luter.heimdall.core.session.scheduler.DefaultInvalidSessionClearScheduler;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Slf4j
//public class CaffeineHeimdallConfig {
//    @Bean
//    public LoginPasswordRetryLimit loginPasswordRetryLimit() {
//        final CaffeineLoginPasswordRetryLimitImpl loginPasswordRetryLimit = new CaffeineLoginPasswordRetryLimitImpl();
//        loginPasswordRetryLimit.setAttemptLimit(5);
//        loginPasswordRetryLimit.setLockedDuration(60);
//        return loginPasswordRetryLimit;
//    }
//
//    @Bean
//    public SessionDAO sessionDAO(ServletHolder servletHolder) {
//        log.warn("初始化 SessionDAO");
//        return new CaffeineSessionDaoImpl(servletHolder);
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(SessionDAO sessionDAO) {
//        log.warn("初始化 认证管理器");
//        return new AuthenticationManager(sessionDAO);
//    }
//
//    @Bean
//    public AuthorizationMetaDataCacheDao authorizationMetaDataCacheDao() {
//        log.warn("初始化 系统权限数据 MetaDataDao");
//        return new CaffeineAuthorizationMetaDataDao();
//    }
//
//    @Bean
//    public AuthorizationManager authorizationManager(AuthenticationManager authenticationManager
//            , AuthorizationMetaDataService authorizationMetaDataService, AuthorizationMetaDataCacheDao authorizationMetaDataCacheDao) {
//        log.warn("初始化 授权管理器");
//        return new AuthorizationManager(authorizationMetaDataService,
//                authorizationMetaDataCacheDao, authenticationManager);
//    }
//
//    @Bean
//    public DefaultInvalidSessionClearScheduler defaultInvalidSessionClearScheduler(SessionDAO sessionDAO) {
//        log.warn("初始化 Session 自动清理任务");
//        return new DefaultInvalidSessionClearScheduler(sessionDAO);
//    }
//}
