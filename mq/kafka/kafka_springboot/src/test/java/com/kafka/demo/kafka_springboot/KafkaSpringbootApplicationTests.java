package com.kafka.demo.kafka_springboot;

import com.kafka.demo.kafka_springboot.producer.Demo1Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Slf4j
class KafkaSpringbootApplicationTests {

    @Autowired
    private Demo1Producer producer;


    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException{
        String id =  String.valueOf (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号: [{}] 发送结果: [{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testAsyncSend() throws InterruptedException{
        String id =  String.valueOf (System.currentTimeMillis() / 1000);
        producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable e) {
                log.info("[testAsyncSend][发送编号:[{}] 发送异常]", id, e);
            }
            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                log.info("[testAsyncSend][发送编号:[{}] 发送成功，结果为: [{}]]", id, result);
            }
        });

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }



    @Test
    void contextLoads() {
    }

}
