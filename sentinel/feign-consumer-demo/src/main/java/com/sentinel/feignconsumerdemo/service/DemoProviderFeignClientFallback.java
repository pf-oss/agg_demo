package com.sentinel.feignconsumerdemo.service;


/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/26 17:25
 */
public class DemoProviderFeignClientFallback implements DemoProviderFeignClient {

    private Throwable throwable;

    public DemoProviderFeignClientFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String echo() {
        return "fallback:" + throwable.getClass().getSimpleName();
    }

}
