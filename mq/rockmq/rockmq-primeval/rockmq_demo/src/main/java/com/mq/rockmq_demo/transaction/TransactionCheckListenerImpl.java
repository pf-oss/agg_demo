package com.mq.rockmq_demo.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Description:  未决事务， 服务器会查客户端
 * @author: pengfei_yao
 * @create: 2019/11/7 11:20
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {

    // 在这里， 我们可以根据由MQ回传的key去数据库查询，这条数据到底是成功了，还是失败了
    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt msg) {

        System.out.println("未决事务， 服务器回查客户端msg = " + new String(msg.getBody().toString()));
        return LocalTransactionState.ROLLBACK_MESSAGE;
    }
}
