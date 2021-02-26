package com.springboot.bro.kafka__springboot_bro.consumer;

// Demo04Consumer.java

import com.springboot.bro.kafka__springboot_bro.message.Demo05Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/17 15:03
 */
@Component
@Slf4j
public class Demo05Consumer {

    @KafkaListener(topics = Demo05Message.TOPIC,
            groupId = "demo05-consume-group-" + Demo05Message.TOPIC + "-" + "#{T(java.util.UUID).randomUUID()}") // <X>
    public void onMessage(Demo05Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
