package com.mq.rockmq_demo2.singlemode;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Description:  3.0 以单向模式发送消息
 *
 *  单向传输用于要求中等可靠性的情况，例如日志搜集
 *
 * @author: pengfei_yao
 * @create: 2019/11/14 16:29
 */
public class OneWayProducer {

    public static void main(String[] args) throws Exception{

        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        for (int i = 0; i < 100; i++){
            Message msg = new Message("TopicTest", "TagA", ("Hello RockMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(msg);
        }

        producer.shutdown();
    }
}
