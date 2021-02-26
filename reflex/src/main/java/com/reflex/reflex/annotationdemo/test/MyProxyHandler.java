package com.reflex.reflex.annotationdemo.test;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/9 13:53
 */
@Slf4j
public class MyProxyHandler {

    /**
     * @Description: 没有被过滤掉的方法，最后是回到这个方法被调用实际的方法
     * @param a:
     * @return: java.lang.Object
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        log.info(method.getName() + "方法被调用了!");
        Object obj = method.invoke(proxy, args);
        log.info(method.getName() + "方法调用结束！");
        return obj;
    }
}
