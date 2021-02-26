package com.mq.rockmq_demo.single;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @Description:  发送顺序消息
 * @author: pengfei_yao
 * @create: 2019/11/6 14:54
 */
public class Producer {

    public static void main(String[] args){

        try{
            DefaultMQProducer producer = new DefaultMQProducer("order_producer");
            producer.setNamesrvAddr("127.0.0.1:9876");

            producer.start();
            for (int i = 1; i <= 5; i++){
                Message msg = new Message("topicOrderTest",  "order_1", "KEY" + i, ("order_1 " + i).getBytes());
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message message, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 0);
                System.out.println(sendResult);
            }

            producer.shutdown();
        }catch (MQClientException e){
            e.printStackTrace();
        }catch (RemotingException e){
            e.printStackTrace();
        }catch (MQBrokerException e){
            e.printStackTrace();
        }catch (InterruptedException e){

        }
    }
}
