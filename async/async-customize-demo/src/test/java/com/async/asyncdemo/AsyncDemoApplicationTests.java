package com.async.asyncdemo;

import com.async.asyncdemo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class AsyncDemoApplicationTests {

    @Autowired
    private DemoService demoService;

    @Test
    public void task01() throws Exception{
        long now = System.currentTimeMillis();
        log.info("[task01][开始执行]");

        demoService.execute01();
        demoService.execute02();

        // sleep 1 秒，保证异步调用的执行
        Thread.sleep(1000);
        log.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }






}
