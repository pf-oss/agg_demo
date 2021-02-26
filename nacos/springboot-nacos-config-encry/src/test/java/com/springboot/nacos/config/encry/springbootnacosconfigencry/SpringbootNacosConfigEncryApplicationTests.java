package com.springboot.nacos.config.encry.springbootnacosconfigencry;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootNacosConfigEncryApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void encode() {
        String applicationName = "demo-application";
        System.out.println(encryptor.encrypt(applicationName));
    }

    @Test
    void contextLoads() {
    }

}
