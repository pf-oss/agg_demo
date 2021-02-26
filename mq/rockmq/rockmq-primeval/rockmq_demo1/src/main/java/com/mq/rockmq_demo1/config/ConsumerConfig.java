package com.mq.rockmq_demo1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Description:  RocketMQ xiao
 * @author: pengfei_yao
 * @create: 2019/11/7 15:06
 */
@Configuration
@Slf4j
public class ConsumerConfig {
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocket.consumer.groupName}")
    private String groupName;
    @Value("${rocket.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${rocketmq.consumer.topics}")
    private String topics;
    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;
    @Resource
    private RocketMsgListener msgListener;

}
