package com.sentinel.feignconsumerdemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/26 17:24
 */
@FeignClient(name = "demo-provider", url = "http://127.0.0.1:8082",
        fallbackFactory = DemoProviderFeignClientFallbackFactory.class)
public interface DemoProviderFeignClient {

    @GetMapping("/demo/test")
    String echo();

}
