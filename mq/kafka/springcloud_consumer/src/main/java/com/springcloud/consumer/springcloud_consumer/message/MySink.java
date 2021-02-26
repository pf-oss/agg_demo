package com.springcloud.consumer.springcloud_consumer.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/18 15:34
 */

public interface MySink {

    String DEMO01_INPUT = "demo01-input";

    @Input(DEMO01_INPUT)
    SubscribableChannel demo01Input();
}
