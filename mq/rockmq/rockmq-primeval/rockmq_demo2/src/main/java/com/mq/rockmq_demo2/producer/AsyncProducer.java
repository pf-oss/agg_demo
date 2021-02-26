package com.mq.rockmq_demo2.producer;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Description:  2.0 异步发送消息
 * @author: pengfei_yao
 * @create: 2019/11/14 16:08
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception{

        DefaultMQProducer producer = new DefaultMQProducer("please_rename_group_name_1");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        producer.setRetryTimesWhenSendAsyncFailed(1000);
        for (int i = 0; i < 100; i++){

            final int index = i;
            Message msg = new Message("TopicTest", "TagA", "OrderID188", "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));

            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("生产消息返回信息 sendResult = " + sendResult);
                    System.out.printf("生产者消息 %-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d OK %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        producer.shutdown();
    }
}
