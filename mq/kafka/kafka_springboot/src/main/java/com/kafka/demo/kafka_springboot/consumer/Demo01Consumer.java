package com.kafka.demo.kafka_springboot.consumer;

import com.kafka.demo.kafka_springboot.message.Demo01Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/16 17:18
 */
@Component
@Data
@Slf4j
public class Demo01Consumer {

    @KafkaListener(topics = Demo01Message.TOPIC,
            groupId = "demo01-consume-group-" + Demo01Message.TOPIC)
    public void onMessage(Demo01Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
