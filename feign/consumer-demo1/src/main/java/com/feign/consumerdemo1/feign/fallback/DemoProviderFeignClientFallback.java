package com.feign.consumerdemo1.feign.fallback;

import com.feign.consumerdemo1.dto.DemoDTO;
import com.feign.consumerdemo1.feign.DemoProviderFeignClient;

import java.util.Map;

public class DemoProviderFeignClientFallback implements DemoProviderFeignClient {

    private Throwable throwable;

    public DemoProviderFeignClientFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String echo(String name) {
        return "fallback:" + throwable.getClass().getSimpleName();
    }

    @Override
    public DemoDTO getDemo(DemoDTO demoDTO) {
        return null;
    }

    @Override
    public DemoDTO getDemo(String username, String password) {
        return null;
    }

    @Override
    public DemoDTO getDemo(Map<String, Object> params) {
        return null;
    }

    @Override
    public DemoDTO postDemo(DemoDTO demoDTO) {
        return null;
    }
}
