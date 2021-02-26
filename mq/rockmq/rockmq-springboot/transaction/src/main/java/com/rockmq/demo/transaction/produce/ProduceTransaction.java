package com.rockmq.demo.transaction.produce;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class ProduceTransaction {

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Value("${demo.rocketmq.transTopic}")
    private String springTransTopic;
    @Value("${demo.rocketmq.topic}")
    private String springTopic;
    @Value("${demo.rocketmq.topic.user}")
    private String userTopic;
    @Value("${demo.rocketmq.orderTopic}")
    private String orderPaidTopic;
    @Value("${demo.rocketmq.msgExtTopic}")
    private String msgExtTopic;
    @Value("${demo.rocketmq.stringRequestTopic}")
    private String stringRequestTopic;
    @Value("${demo.rocketmq.bytesRequestTopic}")
    private String bytesRequestTopic;
    @Value("${demo.rocketmq.objectRequestTopic}")
    private String objectRequestTopic;
    @Value("${demo.rocketmq.genericRequestTopic}")
    private String genericRequestTopic;
//
//    @Autowired
//    private RocketMQTemplate extRocketMQTemplate;

    public void testRocketMQTemplateTransaction() throws MessagingException {
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {

                Message msg = MessageBuilder.withPayload("rocketMQTemplate transactional message " + i).
                        setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + i).build();
                SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(
                        springTransTopic + ":" + tags[i % tags.length], msg, null);
                System.out.printf("------rocketMQTemplate send Transactional msg body = %s , sendResult=%s %n",
                        msg.getPayload(), sendResult.getSendStatus());

                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    private void testExtRocketMQTemplateTransaction() throws MessagingException {
//        for (int i = 0; i < 10; i++) {
//            try {
//                Message msg = MessageBuilder.withPayload("extRocketMQTemplate transactional message " + i).
//                        setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + i).build();
//                SendResult sendResult = extRocketMQTemplate.sendMessageInTransaction(
//                        springTransTopic, msg, null);
//                System.out.printf("------ExtRocketMQTemplate send Transactional msg body = %s , sendResult=%s %n",
//                        msg.getPayload(), sendResult.getSendStatus());
//
//                Thread.sleep(10);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @RocketMQTransactionListener
    class TransactionListenerImpl implements RocketMQLocalTransactionListener {
        private AtomicInteger transactionIndex = new AtomicInteger(0);

        private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<String, Integer>();

        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            log.info("本地事务");

//            String transId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
//            System.out.printf("#### executeLocalTransaction is executed, msgTransactionId=%s %n",
//                    transId);
//            int value = transactionIndex.getAndIncrement();
//            int status = value % 3;
//            localTrans.put(transId, status);
//            if (status == 0) {
//                // Return local transaction with success(commit), in this case,
//                // this message will not be checked in checkLocalTransaction()
//                System.out.printf("    # COMMIT # Simulating msg %s related local transaction exec succeeded! ### %n", msg.getPayload());
//                return RocketMQLocalTransactionState.COMMIT;
//            }
//
//            if (status == 1) {
//                // Return local transaction with failure(rollback) , in this case,
//                // this message will not be checked in checkLocalTransaction()
//                System.out.printf("    # ROLLBACK # Simulating %s related local transaction exec failed! %n", msg.getPayload());
//                return RocketMQLocalTransactionState.ROLLBACK;
//            }
//
//            System.out.printf("    # UNKNOW # Simulating %s related local transaction exec UNKNOWN! \n");
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            log.info("回查事务");

//            String transId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
//            RocketMQLocalTransactionState retState = RocketMQLocalTransactionState.COMMIT;
//            Integer status = localTrans.get(transId);
//            if (null != status) {
//                switch (status) {
//                    case 0:
//                        retState = RocketMQLocalTransactionState.COMMIT;
//                        break;
//                    case 1:
//                        retState = RocketMQLocalTransactionState.ROLLBACK;
//                        break;
//                    case 2:
//                        retState = RocketMQLocalTransactionState.UNKNOWN;
//                        break;
//                }
//            }
//            System.out.printf("------ !!! checkLocalTransaction is executed once," +
//                            " msgTransactionId=%s, TransactionState=%s status=%s %n",
//                    transId, retState, status);
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

//    @RocketMQTransactionListener(rocketMQTemplateBeanName = "extRocketMQTemplate")
//    class ExtTransactionListenerImpl implements RocketMQLocalTransactionListener {
//        @Override
//        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
//            System.out.printf("ExtTransactionListenerImpl executeLocalTransaction and return UNKNOWN. \n");
//            return RocketMQLocalTransactionState.UNKNOWN;
//        }
//
//        @Override
//        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
//            System.out.printf("ExtTransactionListenerImpl checkLocalTransaction and return COMMIT. \n");
//            return RocketMQLocalTransactionState.COMMIT;
//        }
//    }



}
