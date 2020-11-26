package com.sentinel.feignconsumerdemo.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoProviderFeignClientFallbackFactory implements FallbackFactory<DemoProviderFeignClientFallback> {

    @Override
    public DemoProviderFeignClientFallback create(Throwable throwable) {
        // 可以给 DemoProviderFeignClientFallback 提供具体的 throwable 异常
        return new DemoProviderFeignClientFallback(throwable);
    }

}
