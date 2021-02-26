package com.kafka.demo.kafka_springboot.producer;

import com.kafka.demo.kafka_springboot.message.Demo01Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/16 17:05
 */
@Component
public class Demo1Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;


    /**
     * @Description: 同步发送
     * @param id:
     * @return: org.springframework.kafka.support.SendResult
     */
    public SendResult syncSend(String id) throws ExecutionException, InterruptedException{
        // 创建Demo1Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo01Message.TOPIC, message).get();
    }

    public ListenableFuture<SendResult<Object, Object>> asyncSend(String id){
        // 创建Demo01Message消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 异步发送消息
        return kafkaTemplate.send(Demo01Message.TOPIC, message);
    }


}
