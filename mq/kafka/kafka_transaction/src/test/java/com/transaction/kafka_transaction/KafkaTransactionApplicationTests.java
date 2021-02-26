package com.transaction.kafka_transaction;

import com.transaction.kafka_transaction.producer.Demo07Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Slf4j
class KafkaTransactionApplicationTests {
    @Autowired
    private Demo07Producer producer;

    @Test
    public void testSyncSendInTransaction() throws Exception{
      //  int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSendInTransaction(String.valueOf(1), new Runnable() {

            @Override
            public void run() {
                log.info("[run][我要开始睡觉了]");

                try {
                    Thread.sleep(10 * 1000L);
//                    throw new Exception() ;
                } catch (Exception e) {
                    throw new RuntimeException("fdsfsdf");
                }
         //       log.info("[run][我睡醒了]");
            }

        });

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


}
