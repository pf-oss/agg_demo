package com.async.asyncdemo.core;


import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/15 14:57
 */
@Component
@Slf4j
public class GlobalAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {


    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... params) {
        log.error("[handleUncaughtException][method({}) params({}) 发生异常]",
                method, params, throwable);
    }
}
