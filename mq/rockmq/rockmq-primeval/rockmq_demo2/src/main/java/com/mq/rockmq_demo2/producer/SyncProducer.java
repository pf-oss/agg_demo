package com.mq.rockmq_demo2.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Description:  1.0 同步发送消息
 * @author: pengfei_yao
 * @create: 2019/11/14 16:07
 */
public class SyncProducer {


    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        for (int i = 0; i < 100; i++){

            Message msg = new Message("TopicTest", "TagA", ("Hello RockMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

            SendResult sendResult = producer.send(msg);
          //  System.out.printf("%s%n", sendResult);
            System.out.println("响应结果 : " + sendResult);
        }
        producer.shutdown();
    }
}
