package com.springboot.nacos.config.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/9/23 11:09
 */
@Component
@Slf4j
public class ValueCommandLineRunner implements CommandLineRunner {


    @Value(value = "${order.pay-timeout-seconds}")
    private Integer payTimeoutSeconds;

    @Value(value = "${order.create-frequency-seconds}")
    private Integer createFrequencySeconds;

    @Override
    public void run(String... args) throws Exception {

        log.info("value command payTimeOutSeconds：" + payTimeoutSeconds);
        log.info("crateFrequencySeconds：" + createFrequencySeconds);
    }
}
