package com.mq.rockmq_demo.multi;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Consumer2 {

    public static void main(String[] args) throws MQClientException{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_consumer");

        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("topicOrderTest", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            AtomicLong consumer = new AtomicLong(0);
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 设置自动提交
                context.setAutoCommit(true);
                for (MessageExt msg : msgs){
                    System.out.println(msg + ", 内容 :" + new String(msg.getBody()));
                }
                try{
                    TimeUnit.SECONDS.sleep(5L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return null;
            }
        });
        consumer.start();
        System.out.println("Consumer2 started.......");
    }
}
