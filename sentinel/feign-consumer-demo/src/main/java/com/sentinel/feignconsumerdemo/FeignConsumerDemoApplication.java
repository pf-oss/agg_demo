package com.sentinel.feignconsumerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignConsumerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerDemoApplication.class, args);
    }

}
