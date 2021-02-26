package com.springcloud.nacosconfig.springcloudnacosconfig.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/9/24 14:04
 */
@Component
@ConfigurationProperties(prefix = "order")
@Data
public class OrderProperties {

    /**
     * 订单支付超时时长，单位: 秒
     */
    private Integer payTimeOutSeconds;

    /**
     * 订单创建频率，单位: 秒
     */
    private Integer createFrequencySeconds;
}
