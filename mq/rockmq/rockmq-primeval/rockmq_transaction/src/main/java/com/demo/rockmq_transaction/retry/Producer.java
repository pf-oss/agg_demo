package com.demo.rockmq_transaction.retry;


import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Description:  消息生产端进行重试操作
 * @author: pengfei_yao
 * @create: 2019/11/15 13:56
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("group_name");

        producer.setNamesrvAddr("127.0.0.1:9876");

        // 失败的情况下重试3次
        producer.setRetryTimesWhenSendAsyncFailed(3);

        producer.start();

        for (int i = 0; i < 100; i++){
            try{
                Message msg = new Message("TopicTest", "TagA", ("HelloWorld - RocketMQ" + i).getBytes());

                // 消息在1s内没有发送成功，就会重试
                SendResult sendResult = producer.send(msg, 1000);
                System.out.println("发送结果 sendResult : " + sendResult);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("发送失败! ");
                Thread.sleep(1000);
            }

        }
    }
}
