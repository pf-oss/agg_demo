package com.config.configurationcommon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 类上的配置
 * @author: pf
 * @create: 2020/12/17 11:12
 */
@ConfigurationProperties(prefix = "order")
@Data
public class OrderProperties {

    /**
     * 订单支付超时时长，单位：秒。
     */
    private Integer payTimeoutSeconds;

    /**
     * 订单创建频率，单位：秒
     */
    private Integer createFrequencySeconds;


}
