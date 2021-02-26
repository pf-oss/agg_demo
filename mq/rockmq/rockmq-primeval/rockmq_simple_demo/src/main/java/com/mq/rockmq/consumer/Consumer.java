package com.mq.rockmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class Consumer {

    /**
     *  消费者的组名
     */
    @Value("${apache.rockmq.consumer.PushConsumer}")
    private String consumerGroup;

    /**
     *  NameServer地址
     */
    @Value("${apache.rockmq.namesrvAddr}")
    private String namesrvAddr;

    @PostConstruct
    public void defaultMQPushConsumer(){

        // 消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

        // 指定NameServer地址， 多个地址以; 隔开
        consumer.setNamesrvAddr(namesrvAddr);

        try{

            // 订阅PushTopic下Tag的push的消息
            consumer.subscribe("PushTopic", "push");

            // 设置Consumer第一次启动是从队列头部开始消费化石队列尾部开始消费
            // 如果非第一次启动, 那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                  try{
                      for (MessageExt messageExt : list){
                          // 输出消息内容
                          System.out.println("messageExt : " + messageExt);
                          String messageBody = new String(messageExt.getBody(), "utf-8");
                          // 输出消息内容
                          System.out.println("消息响应 :" + messageExt.getMsgId() +  ", msgBody : " + messageBody);
                      }
                  }catch (Exception e){
                      e.printStackTrace();
                      return ConsumeConcurrentlyStatus.RECONSUME_LATER; // 稍后再试
                  }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 消费成功
                }
            });
            consumer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
