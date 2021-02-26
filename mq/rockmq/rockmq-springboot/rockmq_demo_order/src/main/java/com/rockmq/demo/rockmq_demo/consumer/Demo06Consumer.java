package com.rockmq.demo.rockmq_demo.consumer;

import com.rockmq.demo.rockmq_demo.message.Demo06Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
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
@RocketMQMessageListener(topic = Demo06Message.TOPIC, consumerGroup = "demo06-consume-group-" + Demo06Message.TOPIC, consumeMode = ConsumeMode.ORDERLY)
public class Demo06Consumer implements RocketMQListener<Demo06Message> {

    @Override
    public void onMessage(Demo06Message message){
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);

        // sleep 2 秒，用于查看顺序消费的效果
        try {
            Thread.sleep(2 * 1000L);
        } catch (InterruptedException ignore) {
        }

    }
}
