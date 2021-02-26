package com.reflex.reflex.dynamicProxy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/8 9:55
 */
@Slf4j
public class ProxyHandler implements InvocationHandler {

    private StudentIF student;

    public ProxyHandler(StudentIF student){
        this.student = student;
    }


    /**
     * @Description:
     * @param proxy: 代理对象
     * @param method: 调用的方法
     * @param args:  方法参数
     * @return: java.lang.Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("调用了" + method.getName() + "方法");
        // 这里最核心的一句代码，这句代码调用了实现类的方法，如果方法有返回值会存放obj变量里
        // arg接口类型参数
        System.out.println("在被代理的方法执行之前处理的信息..............");
        Object obj = method.invoke(student, args);
        System.out.println("在被代理的方法执行之后处理的信息..............");
        log.info(method.getName() + "方法调用完毕");
        log.info(method.getName() + "方法的返回值" + obj);
        // 返回实际方法的返回值
        return obj;

    }
}
