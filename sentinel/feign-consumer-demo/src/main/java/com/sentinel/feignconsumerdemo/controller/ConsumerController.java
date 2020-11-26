package com.sentinel.feignconsumerdemo.controller;


import com.sentinel.feignconsumerdemo.service.DemoProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/26 17:27
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private DemoProviderFeignClient demoProviderFeignClient;

    @GetMapping("/echo")
    public String echo() {
        return demoProviderFeignClient.echo();
    }

}
