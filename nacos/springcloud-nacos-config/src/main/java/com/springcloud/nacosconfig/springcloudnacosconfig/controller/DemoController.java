package com.springcloud.nacosconfig.springcloudnacosconfig.controller;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.nacosconfig.springcloudnacosconfig.config.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/9/24 14:08
 */
@RestController
@RequestMapping("/demo")
@RefreshScope
public class DemoController {

    @Autowired
    private OrderProperties orderProperties;

    @GetMapping("/test01")
    public OrderProperties test01() {
        return orderProperties;
    }

    @Value(value = "${order.pay-timeout-seconds}")
    private Integer payTimeOutSeconds;

    @Value(value = "${order.create-frequency-seconds}")
    private Integer createFrequencySeconds;


    @GetMapping("/test02")
    public Map<String, Object> test02() {
        return new JSONObject().fluentPut("payTimeoutSeconds", payTimeOutSeconds)
                .fluentPut("createFrequencySeconds", createFrequencySeconds);
    }


}
