package com.demo.rockmq_transaction.retry;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Description:  订阅消息 消息重试 Exception的情况下
 * @author: pengfei_yao
 * @create: 2019/11/15 14:09
 */
public class Consumer {

    public static void main(String[] args)throws InterruptedException, MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_name");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTest", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try{
                    MessageExt msg = msgs.get(0);
                    String msgBody = new String(msg.getBody(), "utf-8");
                    System.out.println(msgBody + "Receive New Messages : " + msgs);
                    if (msgBody.equals("HellWorld - RocketMQ4")){
                        System.out.println("==============错误==============");
                        int a = 1/0;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if (msgs.get(0).getReconsumeTimes() == 3){
                        // 该条消息可以存储到DB或者log日志中，或其它处理方式
                        // 成功
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }else {
                        // 重试
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }

}
