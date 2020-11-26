package com.sentinel.limit.sentinellimitstreamdemo.handler;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/26 14:10
 */
@Component
@ControllerAdvice(basePackages = "com.sentinel.limit.sentinellimitstreamdemo.controller")
public class GlobalExceptionHandler {

    /**
     * @Description:  因为这里是示例，所以暂时使用 JSONObject，实际项目最终定义一个 CommonResult。
     * @param blockException:
     * @return: com.alibaba.fastjson.JSONObject
     */
    @ResponseBody
    @ExceptionHandler(value = BlockException.class)
    public JSONObject blockExceptionHandler(BlockException blockException) {
        return new JSONObject().fluentPut("code", 1024)
                .fluentPut("msg", "请求被拦截，拦截类型为 " + blockException.getClass().getSimpleName());
    }

}
