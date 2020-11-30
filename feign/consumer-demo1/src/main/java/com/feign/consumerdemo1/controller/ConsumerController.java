package com.feign.consumerdemo1.controller;

import com.feign.consumerdemo1.dto.DemoDTO;
import com.feign.consumerdemo1.feign.DemoProviderFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 1
 * @author: pengfei_yao
 * @create: 2020/11/30 14:17
 */

@RestController
public class ConsumerController {


    @Resource
    private DemoProviderFeignClient demoProviderFeignClient;

    @GetMapping("/test_get_demo")
    public DemoDTO testGetDemo(@RequestParam("type") int type, DemoDTO demoDTO) {
        // 方式一
        if (type == 1) {
            return demoProviderFeignClient.getDemo(demoDTO);
        } else if (type == 2) {
            return demoProviderFeignClient.getDemo(demoDTO.getUsername(), demoDTO.getPassword());
        } else {
            // 方式三
            Map<String, Object> params = new HashMap<>();
            params.put("username", demoDTO.getUsername());
            params.put("password", demoDTO.getPassword());
            return demoProviderFeignClient.getDemo(params);
        }
    }

    @GetMapping("/test_post_demo")
    public DemoDTO testPostDemo(DemoDTO demoDTO) {
        return demoProviderFeignClient.postDemo(demoDTO);
    }


//    @GetMapping("/echo")
//    public String echo(String name) {
//        return demoProviderFeignClient.echo(name);
//    }


}
