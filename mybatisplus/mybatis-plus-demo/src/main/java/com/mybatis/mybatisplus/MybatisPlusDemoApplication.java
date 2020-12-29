package com.mybatis.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/28 15:18
 */
@SpringBootApplication
//@MapperScan("com.mybatis.mybatisplus.dao")
public class MybatisPlusDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDemoApplication.class, args);
    }

}
