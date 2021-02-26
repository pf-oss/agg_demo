package com.transaction.kafka_transaction.message;

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
public class Demo07Message {

    public static final String TOPIC = "DEMO_07";

    private String id;
}
