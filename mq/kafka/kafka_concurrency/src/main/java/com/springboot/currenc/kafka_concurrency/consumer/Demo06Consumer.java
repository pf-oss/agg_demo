package com.springboot.currenc.kafka_concurrency.consumer;

import com.springboot.currenc.kafka_concurrency.message.Demo06Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// Demo06Consumer.java

@Component
@Slf4j
public class Demo06Consumer {


    @KafkaListener(topics = Demo06Message.TOPIC,
            groupId = "demo06-consume-group-" + Demo06Message.TOPIC,
            concurrency = "2")
    public void onMessage(Demo06Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}

