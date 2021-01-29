package com.luter.heimdall.admin.config;

import com.luter.heimdall.starter.jpa.config.AbstractTransactionAdviceConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Aspect
@Configuration
@Slf4j
@EnableTransactionManagement(proxyTargetClass = true)
public class TxConfig extends AbstractTransactionAdviceConfig {

}
