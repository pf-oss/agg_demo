package com.springcloud.consumer.springcloud_consumer.Listener;

import com.springcloud.consumer.springcloud_consumer.message.Demo01Message;
import com.springcloud.consumer.springcloud_consumer.message.MySink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/18 15:45
 */
@Component
@Slf4j
public class Demo01Consumer {

    @StreamListener(MySink.DEMO01_INPUT)
    public void onMessage(@Payload Demo01Message message){
        log.info("[消费者消费消息==onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
