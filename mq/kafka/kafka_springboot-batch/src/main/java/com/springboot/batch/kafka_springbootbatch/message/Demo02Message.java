package com.springboot.batch.kafka_springbootbatch.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/17 10:49
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Demo02Message {

    public static final String TOPIC = "DEMO_012";

    private String id;
}
