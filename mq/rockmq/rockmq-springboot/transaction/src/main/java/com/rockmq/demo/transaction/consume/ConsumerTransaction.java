package com.rockmq.demo.transaction.consume;


import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "${demo.rocketmq.transTopic}", consumerGroup = "string_trans_consumer")
public class ConsumerTransaction implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.printf("------- 消费 received: %s \n", message);
    }
}
