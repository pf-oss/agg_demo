package com.mq.rockmq_demo1.config;

import com.mq.rockmq_demo1.service.ParamConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:  消息消费监听
 * @author: pengfei_yao
 * @create: 2019/11/7 15:16
 */
@Component
@Slf4j
public class RocketMsgListener implements MessageListenerConcurrently {

    @Resource
    private ParamConfigService paramConfigService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {

        if (CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        log.info("接收到的消息为：" + new String(messageExt.getBody()));
        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次， 如果不需要再次消费，则返回成功
        if (reConsume == 3){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        if (messageExt.getTopic().equals(paramConfigService.rocketTopic)){
            String tags = messageExt.getTags();
            switch (tags){
                case "rocketTag":
                    log.info("开户 tag == >>>" + tags);
                    break;
                default:
                    log.info("未匹配到tag ==" +tags);
                    break;
            }
        }
        // 消息消费成功
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
