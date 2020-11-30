package com.feign.providerdemo1.controller;

import com.feign.providerdemo1.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/30 16:12
 */
@RestController
@Slf4j
public class ProviderController {

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/get_demo")
    public DemoDTO getDemo(DemoDTO demoDTO) {
        return demoDTO;
    }

    @PostMapping("/post_demo")
    public DemoDTO postDemo(@RequestBody DemoDTO demoDTO) {
        return demoDTO;
    }

    @GetMapping("/echo")
    public String echo(String name) throws InterruptedException {
        // 模拟执行 100ms 时长。方便后续我们测试请求超时
//        Thread.sleep(100L);
        // 记录被调用的日志
        log.info("[echo][被调用啦 name({})]", name);
        return serverPort + "-provider:" + name;
    }

}
