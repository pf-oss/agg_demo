package com.mq.rockmq_demo1.service;

import org.apache.rocketmq.client.producer.SendResult;

public interface RocketMqService {
    SendResult openAccountMsg(String msgInfo);
}
