package com.mq.rockmq_demo.single;


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

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2019/11/6 15:05
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException{

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_consumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");

        /**
         *  设置consumer第一次启动是从队列开始消费还是从队尾开始消费
         *  如果非第一次启动， 那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("topicOrderTest", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            AtomicLong atomicLong = new AtomicLong(0);
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 设置自动提交
                context.setAutoCommit(true);
                for (MessageExt msg : msgs){
                    System.out.println(msg + ",内容：" + new String(msg.getBody()));
                }
                try{
                    TimeUnit.SECONDS.sleep(5L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer1 Started........");
    }
}
