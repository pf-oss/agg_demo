package com.feign.consumerdemo1.feign;

import com.feign.consumerdemo1.dto.DemoDTO;
import com.feign.consumerdemo1.feign.fallback.DemoProviderFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "feign-provider-demo1", fallbackFactory = DemoProviderFeignClientFallbackFactory.class)
public interface DemoProviderFeignClient  {

    /**
     * @Description:  GET 方式一，最推荐
     * @param demoDTO:
     * @return: com.feign.consumerdemo1.dto.DemoDTO
     */
    @GetMapping("/get_demo")
    DemoDTO getDemo(@SpringQueryMap DemoDTO demoDTO);

    /**
     * @Description:  GET 方式二，相对推荐
     * @param username:
     * @param password:
     * @return: com.feign.consumerdemo1.dto.DemoDTO
     */
    @GetMapping("/get_demo")
    DemoDTO getDemo(@RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * @Description: GET 方式三，不推荐
     * @param params:
     * @return: com.feign.consumerdemo1.dto.DemoDTO
     */
    @GetMapping("/get_demo")
    DemoDTO getDemo(@RequestParam Map<String, Object> params);

    /**
     * @Description: POST 方式
     * @param demoDTO:
     * @return: com.feign.consumerdemo1.dto.DemoDTO
     */
    @PostMapping("/post_demo")
    DemoDTO postDemo(@RequestBody DemoDTO demoDTO);

    /**
     * @Description:
     * @return: java.lang.String
     */
    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

}