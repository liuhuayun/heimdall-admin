package com.luter.heimdall.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
public class HeimdallAdmin {

    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext application = SpringApplication.run(HeimdallAdmin.class, args);
            Environment env = application.getEnvironment();
            log.info("\n----------------------------------------------------------\n\t" +
                            "应用: '{}' 启动完毕!\n\t" +
                            "地址: \t\thttp://{}:{}\n" +
                            "----------------------------------------------------------",
                    env.getProperty("spring.application.name"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("local.server.port"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
