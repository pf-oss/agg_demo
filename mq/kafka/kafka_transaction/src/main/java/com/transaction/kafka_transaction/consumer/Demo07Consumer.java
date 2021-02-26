package com.transaction.kafka_transaction.consumer;

import com.transaction.kafka_transaction.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo07Consumer {


    @KafkaListener(topics = Demo07Message.TOPIC,
            groupId = "demo07-consume-group-" + Demo07Message.TOPIC)
    public void onMessage(Demo07Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}