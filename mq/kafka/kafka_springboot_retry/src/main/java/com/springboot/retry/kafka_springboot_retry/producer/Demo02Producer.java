package com.springboot.retry.kafka_springboot_retry.producer;

import com.springboot.retry.kafka_springboot_retry.message.Demo02Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/17 10:52
 */

@Component
public class Demo02Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(String id) throws ExecutionException, InterruptedException {
        // 创建 Demo02Message 消息
        Demo02Message message = new Demo02Message();
        message.setId(id);
        // 异步发送消息
        return kafkaTemplate.send(Demo02Message.TOPIC, message).get();
    }

}
