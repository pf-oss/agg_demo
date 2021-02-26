package com.springcloud.producer.springcloud_producer.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/18 15:16
 */
public interface MySource {

    @Output("demo01-output")
    MessageChannel demo01Output();


}
