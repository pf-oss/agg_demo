package com.feign.provider.api.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/30 15:17
 */
public interface ProviderApiService {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

}
