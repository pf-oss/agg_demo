package com.rockmq.demo.transaction;

import com.rockmq.demo.transaction.produce.ProduceTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class TransactionApplicationTests {

    @Autowired
    private ProduceTransaction produceTransaction;


    @Test
    void contextLoads() throws InterruptedException {
        produceTransaction.testRocketMQTemplateTransaction();
        new CountDownLatch(1).await();

    }

}
