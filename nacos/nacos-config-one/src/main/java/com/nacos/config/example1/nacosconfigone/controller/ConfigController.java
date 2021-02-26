package com.nacos.config.example1.nacosconfigone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/9/23 10:16
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {


    @Value("${userLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }

}
