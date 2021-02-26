package com.springboot.currenc.kafka_concurrency.producer;

import com.springboot.currenc.kafka_concurrency.message.Demo06Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/17 10:52
 */

// Demo06Producer.java

@Component
public class Demo06Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(String id) throws ExecutionException, InterruptedException {
        // 创建 Demo01Message 消息
        Demo06Message message = new Demo06Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo06Message.TOPIC, message).get();
    }

    public SendResult syncSendOrderly(String id) throws ExecutionException, InterruptedException {
        // 创建 Demo01Message 消息
        Demo06Message message = new Demo06Message();
        message.setId(id);
        // 同步发送消息
        // 因为我们使用 String 的方式序列化 key ，所以需要将 id 转换成 String
        return kafkaTemplate.send(Demo06Message.TOPIC, id, message).get();
    }

}

