package com.kafka.demo.kafka_springboot.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/16 17:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Demo01Message {

    public static final String TOPIC = "DEMO_01";

    /**
     *  编号
     */
    private String id;
}
