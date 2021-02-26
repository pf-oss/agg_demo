package com.springboot.bro.kafka__springboot_bro.message;

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
public class Demo05Message {

    public static final String TOPIC = "DEMO_05";

    private String id;
}
