package com.feign.consumerdemo1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feign-provider-demo1")
public interface DemoProviderFeignClient {

    /**
     * @Description:
     * @return: java.lang.String
     */
    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

}