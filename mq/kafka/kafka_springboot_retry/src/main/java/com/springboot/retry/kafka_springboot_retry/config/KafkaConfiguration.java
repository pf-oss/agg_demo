package com.springboot.retry.kafka_springboot_retry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/17 13:59
 */
@Configuration
public class KafkaConfiguration {

    @Bean
    @Primary
    public ErrorHandler kafkaErrorHandler(KafkaOperations<?, ?> template){
        // <1> 创建 DeadLetterPublishingRecoverer 对象
        ConsumerRecordRecoverer recordRecoverer = new DeadLetterPublishingRecoverer(template);
        // <2> 创建 FixedBackOff 对象
        BackOff backOff = new FixedBackOff(10 * 1000L, 3L);
        // <3> 创建SeekToCurrentErrorHandler对象
        return new SeekToCurrentErrorHandler(recordRecoverer, backOff);
    }






}
