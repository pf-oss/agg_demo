package com.feign.consumerdemo1.controller;


import com.feign.consumerdemo1.feign.DemoProviderFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Description:  引用在api中的配置
 * @author: pengfei_yao
 * @create: 2020/11/30 18:06
 */
@RestController
public class ConsumerSpanController {

    @Resource
    private DemoProviderFeignClient demoProviderFeignClientTest;

    @GetMapping("/echo")
    public String echo(String name) {
        return demoProviderFeignClientTest.echo(name);
    }
}
