package com.springboot.nacos.config.auto.refresh.springbootnacosconfigauto.controller;


import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/9/23 15:31
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @NacosValue(value = "${test}", autoRefreshed = true)
    private String test;

    @GetMapping("/test")
    public String test() {
        return test;
    }

    @Autowired
    private TestProperties testProperties;

    @GetMapping("/test_properties")
    public TestProperties testProperties() {
        return testProperties;
    }

    @GetMapping("/logger")
    public void logger() {
        log.debug("[logger][测试一下]");
    }
}
