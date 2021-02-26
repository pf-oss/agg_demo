package com.rockmq.demo.rockmq_demo.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/19 14:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demo05Message {

    public static final String TOPIC = "DEMO_05";

    private Integer id;
}
