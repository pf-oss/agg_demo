//package com.feign.providerdemo1.controller;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Description:
// * @author: pengfei_yao
// * @create: 2020/11/30 14:05
// */
//@Slf4j
//@RestController
//public class ProviderTestController {
//
//    @Value("${server.port}")
//    private Integer serverPort;
//
//
//    @GetMapping("/echo")
//    public String echo(String name) throws InterruptedException {
//        // 模拟执行 100ms 时长。方便后续我们测试请求超时
//        Thread.sleep(100L);
//
//        // 记录被调用的日志
//        log.info("[echo][被调用啦 name({})]", name);
//
//        return serverPort + "-provider:" + name;
//    }
//
//}
