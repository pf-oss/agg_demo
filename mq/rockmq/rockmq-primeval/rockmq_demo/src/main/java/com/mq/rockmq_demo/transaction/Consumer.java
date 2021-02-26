package com.mq.rockmq_demo.transaction;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Description:  订阅消息
 * @author: pengfei_yao
 * @create: 2019/11/7 10:55
 */
public class Consumer {

    public  static void main(String[] args) throws InterruptedException, MQClientException{

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction_consumer");

        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeMessageBatchMaxSize(10);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("topicTransactionTest", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try{

                }catch (Exception e){
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; // 重试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 成功
            }
        });

        consumer.start();
        System.out.println("transaction_consumer started...........");
    }
}
