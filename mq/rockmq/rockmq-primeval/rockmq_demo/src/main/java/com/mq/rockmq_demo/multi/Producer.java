package com.mq.rockmq_demo.multi;

import lombok.extern.slf4j.Slf4j;
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
 * @Description: Producer, 发送顺序消息
 * @author: pengfei_yao
 * @create: 2019/11/6 15:37
 */
@Slf4j
public class Producer {

    public static void main(String[] ages){
        try{

            DefaultMQProducer producer = new DefaultMQProducer("order_producer");
            producer.setNamesrvAddr("127.0.0.1:9876");

            producer.start();

            for (int i = 1; i <= 5; i++){
                Message msg = new Message("topicOrderTest", "order_1", "KEY" + i, ("order_1 " + i).getBytes());

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        log.info("mqs = {}, msg = {}, arg = {}" , mqs.toString(), msg.toString(), arg.toString());
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        log.info("index = {}, id = {}, mqs.get(index)", index, id, mqs.get(index));
                        return mqs.get(index);
                    }
                }, 0);
                System.out.println(sendResult);
            }

            for (int i = 1; i <= 5; i ++){
                Message msg = new Message("topicOrderTest", "order_2", "KEY" + i, ("order_2 " + i).getBytes());
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer)arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 1);
                System.out.println(sendResult);
            }

            for (int i =1; i <= 5; i++){
                Message msg = new Message("topicOrderTest", "order_3", "KEY" + i, ("order_3" + i).getBytes());
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 2);
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
            e.printStackTrace();
        }

    }

}
