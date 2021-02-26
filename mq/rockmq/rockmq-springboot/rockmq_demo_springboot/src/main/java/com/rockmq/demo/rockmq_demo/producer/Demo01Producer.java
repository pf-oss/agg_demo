package com.rockmq.demo.rockmq_demo.producer;

import com.rockmq.demo.rockmq_demo.message.Demo01Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/19 14:35
 */
@Component
public class Demo01Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer id){
        // 创建Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        return rocketMQTemplate.syncSend(Demo01Message.TOPIC, message);
    }

    public void asyncSend(Integer id, SendCallback callback){
        // 创建Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 异步发送消息
        rocketMQTemplate.asyncSend(Demo01Message.TOPIC, message, callback);
    }
}
