package com.springboot.nacos.config.auto.refresh.springbootnacosconfigauto.controller;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@NacosConfigurationProperties(dataId = "${nacos.filter.data-id}", type = ConfigType.YAML, autoRefreshed = true)
@Data
public class TestProperties {

    /**
     * 测试属性
     */
    private String test;
}
