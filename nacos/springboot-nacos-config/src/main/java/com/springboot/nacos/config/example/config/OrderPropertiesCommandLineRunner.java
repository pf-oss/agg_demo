package com.springboot.nacos.config.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/9/23 11:00
 */
@Component
@Slf4j
public class OrderPropertiesCommandLineRunner implements CommandLineRunner {

    @Autowired
    private OrderProperties orderProperties;


    @Override
    public void run(String... args) throws Exception {
        log.info("payTimeoutSeconds：" + orderProperties.getPayTimeoutSeconds());
        log.info("createFrequencySeconds:" + orderProperties.getCreateFrequencySeconds());
    }

}
