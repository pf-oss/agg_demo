package com.springcloud.producer.springcloud_producer.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/18 15:20
 */
@RestController
@RequestMapping("/demo01")
public class Demo01Controller {

    @Autowired
    private MySource mySource;

    @GetMapping("/send")
    public boolean send(){
        //<1> 创建Message
        Demo01Message message = new Demo01Message();
        message.setId(new Random().nextInt());
        // <2> 创建Spring Message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message).build();
        // <3> 发送消息
        return mySource.demo01Output().send(springMessage);
    }
}
