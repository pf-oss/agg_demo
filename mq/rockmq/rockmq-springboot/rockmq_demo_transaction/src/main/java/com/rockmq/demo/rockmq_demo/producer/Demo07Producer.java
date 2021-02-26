package com.rockmq.demo.rockmq_demo.producer;

import com.rockmq.demo.rockmq_demo.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/19 14:35
 */
// Demo07Producer.java

@Component
@Slf4j
public class Demo07Producer {

    private static final String TX_PRODUCER_GROUP = "demo07-producer-group";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public TransactionSendResult sendMessageInTransaction(Integer id) {
        // <1> 创建 Demo07Message 消息
        Demo07Message demo07Message = new Demo07Message();
        demo07Message.setId(id);
        Message message = MessageBuilder.withPayload(demo07Message)
                .build();
        // <2> 发送事务消息
        return rocketMQTemplate.sendMessageInTransaction(Demo07Message.TOPIC, message, id);
//        return rocketMQTemplate.sendMessageInTransaction(TX_PRODUCER_GROUP, Demo07Message.TOPIC, message, id);
    }


    // Demo07Producer.java

    @RocketMQTransactionListener
    public class TransactionListenerImpl implements RocketMQLocalTransactionListener {


        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            // ... local transaction process, return rollback, commit or unknown
            log.info("[executeLocalTransaction][执行本地事务，消息：{} arg：{}]", msg, arg);
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            // ... check transaction status and return rollback, commit or unknown
            log.info("[checkLocalTransaction][回查消息：{}]", msg);
            return RocketMQLocalTransactionState.COMMIT;
        }

    }




}