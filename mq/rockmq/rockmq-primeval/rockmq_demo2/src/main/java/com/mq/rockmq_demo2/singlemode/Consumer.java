package com.mq.rockmq_demo2.singlemode;


import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Description:  消费消息
 * @author: pengfei_yao
 * @create: 2019/11/14 16:37
 */
public class Consumer {


    public static void main(String[] args) throws Exception{

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("nice");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("TopicTest", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                System.out.println("消费者 = " + JSON.toJSONString(context));
                System.out.printf("消费者 %s Receive New Message: %s %n", Thread.currentThread().getName(), msgs);
                for (MessageExt msg : msgs){
                    System.out.println("消费信息 = " + msg.getBody());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("Consumer Started..........%n");
    }


}
