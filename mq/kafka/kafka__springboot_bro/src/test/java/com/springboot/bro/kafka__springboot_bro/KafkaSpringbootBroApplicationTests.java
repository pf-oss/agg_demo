package com.springboot.bro.kafka__springboot_bro;

import com.springboot.bro.kafka__springboot_bro.producer.Demo05Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Slf4j
class KafkaSpringbootBroApplicationTests {

    @Autowired
    private Demo05Producer producer;

    @Test
    public void test() throws InterruptedException{
        // 阻塞等待， 保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException{
        String id = String.valueOf(System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        // 阻塞等待， 保证消费
        new CountDownLatch(1).await();
    }

    @Test
    void contextLoads() {
    }

}
