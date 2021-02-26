package com.rockmq.demo.rockmq_demo.consumer;

import com.rockmq.demo.rockmq_demo.message.Demo04Message;
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
@RocketMQMessageListener(topic = Demo04Message.TOPIC, consumerGroup = "demo04-consume-group-" + Demo04Message.TOPIC)
public class Demo04Consumer implements RocketMQListener<Demo04Message> {

    @Override
    public void onMessage(Demo04Message message){
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        throw new RuntimeException("我就是故意抛出一个异常");
    }
}
