package com.cache.rediscache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.cache.rediscache.dao")
public class RedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCacheApplication.class, args);
    }

}
