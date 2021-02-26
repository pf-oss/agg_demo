package com.mq.rockmq_demo.transaction;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;

/**
 * @Description:  执行本地事务
 * @author: pengfei_yao
 * @create: 2019/11/7 11:10
 */
@Slf4j
public class TransactionExecuterImpl implements LocalTransactionExecuter {

    @Override
    public LocalTransactionState executeLocalTransactionBranch(final Message msg, Object arg) {
        log.info("msg = " + msg.toString());
        System.out.println("执行本地事务msg = " + new String(msg.getBody()));
        System.out.println("执行本地事务arg = " + arg);
        String tags = msg.getTags();
        if (tags.equals("transaction2")){
            System.out.println("======== 我的操作 ========，执行失败! rollback.........");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
