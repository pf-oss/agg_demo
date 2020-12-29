package com.cache.ehcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.cache.ehcache.dao")
public class EhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhcacheApplication.class, args);
    }

}
