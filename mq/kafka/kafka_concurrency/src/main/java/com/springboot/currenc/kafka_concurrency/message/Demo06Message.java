package com.springboot.currenc.kafka_concurrency.message;

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
public class Demo06Message {

    public static final String TOPIC = "DEMO_06";

    private String id;
}
