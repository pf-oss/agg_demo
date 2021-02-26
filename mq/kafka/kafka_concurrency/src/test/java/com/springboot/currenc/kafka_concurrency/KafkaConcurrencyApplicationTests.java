package com.springboot.currenc.kafka_concurrency;

import com.springboot.currenc.kafka_concurrency.producer.Demo06Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Slf4j
class KafkaConcurrencyApplicationTests {

    @Autowired
    private Demo06Producer producer;


    /**
     * @Description: 测试并发消息
     * @return: void
     */
    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException{
        for (int i = 0; i < 10; i ++){
            String id = String.valueOf(System.currentTimeMillis() / 1000);
            SendResult result = producer.syncSend(id);
            log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        }
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


    /**
     * @Description: 测试顺序消息
     * @return: void
     */
    @Test
    public void testSyncSendOrderly() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            int id = 1;
            SendResult result = producer.syncSendOrderly(String.valueOf(id));
            log.info("[testSyncSend][发送编号：[{}] 发送队列：[{}]]", id, result.getRecordMetadata().partition());
        }
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }





}
