package com.rockmq.demo.rockmq_demo;

import com.rockmq.demo.rockmq_demo.producer.Demo04Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
@Slf4j
class RockmqDemoApplicationTests {

    @Autowired
    private Demo04Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }



}
