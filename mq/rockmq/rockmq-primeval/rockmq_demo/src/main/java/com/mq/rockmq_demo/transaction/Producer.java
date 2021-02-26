package com.mq.rockmq_demo.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @Description:  发送事务消息列子
 * @author: pengfei_yao
 * @create: 2019/11/7 11:06
 */       
public class Producer {

    public static void main(String[] args) throws MQClientException, InterruptedException {

        TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("transaction_producer");

        producer.setNamesrvAddr("127.0.0.1:9876");

        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);
        producer.setTransactionCheckListener(transactionCheckListener);
        producer.start();

        TransactionExecuterImpl transactionExecuter = new TransactionExecuterImpl();
        for (int i = 1; i <= 2; i++){
            try {
                Message msg = new Message("topicTransactionTest", "transaction" + i, "KEY" + i, ("Hello RockMQ " + i).getBytes());
                SendResult sendResult = producer.sendMessageInTransaction(msg, transactionExecuter, null);
                System.out.println(sendResult);
                Thread.sleep(10);
            }catch (MQClientException e){
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 100000; i++){
            Thread.sleep(1000);
        }
        producer.shutdown();
    }
}
