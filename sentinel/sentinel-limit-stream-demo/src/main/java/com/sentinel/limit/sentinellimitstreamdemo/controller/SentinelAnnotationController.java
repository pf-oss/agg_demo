package com.sentinel.limit.sentinellimitstreamdemo.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/26 16:35
 */
@RestController
public class SentinelAnnotationController {


    @GetMapping("/annotations_demo")
    @SentinelResource(value = "annotations_demo_resource",
            blockHandler = "blockHandler",
            fallback = "fallback")
    public String annotationsDemo(@RequestParam(required = false) Integer id) throws InterruptedException {
        if (id == null) {
            throw new IllegalArgumentException("id 参数不允许为空");
        }
        return "success...";
    }

    /**
     *  BlockHandler 处理函数，参数最后多一个 BlockException，其余与原函数一致.
     */
    public String blockHandler(Integer id, BlockException ex) {
        return "block：" + ex.getClass().getSimpleName();
    }

    /**
     *  Fallback 处理函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     */
    public String fallback(Integer id, Throwable throwable) {
        return "fallback：" + throwable.getMessage();
    }

}
