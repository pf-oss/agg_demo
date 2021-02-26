package com.reflex.reflex.annotationdemo;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description:  处理类
 * @author: pengfei_yao
 * @create: 2020/5/9 11:34
 */
@Slf4j
public class ProxyHandler implements InvocationHandler {

    /**
     *  obj: 需要代理的操作类对象
     *  handlerObj 用户写的处理类对象
     */
    private Object obj;
    private Object handlerObj;

    public ProxyHandler(Object obj, Object handlerObj) {
        this.obj = obj;
        this.handlerObj = handlerObj;
    }


    /**
     * @Description: 代理对象方法会自动转到这个方法上
     * @param proxy:
     * @param method:
     * @param args:
     * @return: java.lang.Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 声明一个Objec类型变量接受方法的返回值
        Object returnObj = null;
        // 反射出用户写的处理类对象
        Class cls = handlerObj.getClass();
        try {
            // 把ProxyClass注解对象拿出来
            ProxyClass proxyClass = (ProxyClass) obj.getClass().getAnnotation(ProxyClass.class);
            // 判断方法名是否为空
            if (!proxyClass.methodName().equals("")){
                if (method.getName().startsWith(proxyClass.methodName())){
                    // 把用户写的处理类里的invoke方法对象拿出来
                    Method invokeMethod = cls.getMethod("invoke", Object.class, Method.class, Object[].class);
                    // 调用用户写的处理类里的invoke方法，也就是调回了客户那边
                    returnObj = invokeMethod.invoke(handlerObj, obj, method, args);
                }else {
                    // 是需要过滤的方法名，就直接调用
                    return method.invoke(obj, args);
                }
            }else {
                // 为空的话就是没有需要过滤的方法
                Method invokeMethod = cls.getMethod("invoke", Object.class, Method.class, Object[].class);
                returnObj = invokeMethod.invoke(handlerObj, obj, method, args);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return returnObj;
    }
}
