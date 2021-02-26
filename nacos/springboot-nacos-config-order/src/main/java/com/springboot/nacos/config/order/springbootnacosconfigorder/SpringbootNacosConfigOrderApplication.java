package com.springboot.nacos.config.order.springbootnacosconfigorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringbootNacosConfigOrderApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringbootNacosConfigOrderApplication.class, args);
        // 查看 Environment
        Environment environment = context.getEnvironment();
        System.out.println(environment);
    }


}
