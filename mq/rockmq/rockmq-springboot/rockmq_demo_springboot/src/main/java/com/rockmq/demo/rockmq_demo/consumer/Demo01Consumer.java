package com.rockmq.demo.rockmq_demo.consumer;

import com.rockmq.demo.rockmq_demo.message.Demo01Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/19 14:51
 */
@Component
@Data
@Slf4j
@RocketMQMessageListener(topic = Demo01Message.TOPIC, consumerGroup = "demo01-consume-group-" + Demo01Message.TOPIC)
public class Demo01Consumer implements RocketMQListener<Demo01Message> {

    @Override
    public void onMessage(Demo01Message message){
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
