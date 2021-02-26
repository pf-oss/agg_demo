package com.springboot.batch.kafka_springbootbatch.consumer;

import com.springboot.batch.kafka_springbootbatch.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo02Consumer {

    @KafkaListener(topics = Demo02Message.TOPIC,
            groupId = "demo02-consume-group-" + Demo02Message.TOPIC)
    public void onMessage(Demo02Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
