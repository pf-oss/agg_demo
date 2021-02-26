package com.mq.rockmq_demo1.service;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * @Description:  消息发送测试
 * @author: pengfei_yao
 * @create: 2019/11/7 15:48
 */
@Service
public class RocketMqServiceImpl implements RocketMqService {

    @Resource
    private DefaultMQProducer defaultMQProducer;
    @Resource
    private ParamConfigService paramConfigService;

    @Override
    public SendResult openAccountMsg(String msgInfo) {
        // 可以不使用Config的Group
       defaultMQProducer.setProducerGroup(paramConfigService.rocketGroup);
       SendResult sendResult = null;
       try{
           Message sendMsg = new Message(paramConfigService.rocketTopic, paramConfigService.rocketTag, "open_account_key", msgInfo.getBytes());
           sendResult = defaultMQProducer.send(sendMsg);
       }catch (Exception e){
           e.printStackTrace();
       }
        return sendResult;
    }
}
