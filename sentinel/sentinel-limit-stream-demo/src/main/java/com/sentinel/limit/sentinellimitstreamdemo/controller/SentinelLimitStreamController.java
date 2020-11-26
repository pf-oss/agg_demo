package com.sentinel.limit.sentinellimitstreamdemo.controller;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/26 14:12
 */
@RestController
@RequestMapping("/demo")
public class SentinelLimitStreamController {

    @GetMapping("/test")
    public String test() {
        return "这是一个测试方法";
    }

   /**
    *  熔断测试使用
    */
    @GetMapping("/sleep")
    public String sleep() throws InterruptedException {
        Thread.sleep(100L);
        return "sleep";
    }

    /**
     *  热点参数限流
     */
    @GetMapping("/product_info")
    @SentinelResource("demo_product_info_hot")
    public String productInfo(Integer id) {
        return "商品编号：" + id;
    }


    @GetMapping("/entry_demo")
    public String entryDemo() {
        Entry entry = null;
        try {
            // 访问资源
            entry = SphU.entry("entry_demo");
            // 执行业务逻辑
            return "执行成功";
        } catch (BlockException ex) {
            return "被拒绝";
        } finally {
            // 释放资源
            if (entry != null) {
                entry.exit();
            }
        }
    }


}
