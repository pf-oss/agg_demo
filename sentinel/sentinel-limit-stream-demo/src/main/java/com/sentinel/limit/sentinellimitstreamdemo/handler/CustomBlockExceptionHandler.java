package com.sentinel.limit.sentinellimitstreamdemo.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/26 14:05
 */
@Component
public class CustomBlockExceptionHandler implements BlockExceptionHandler {


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        throw e;
    }
}
