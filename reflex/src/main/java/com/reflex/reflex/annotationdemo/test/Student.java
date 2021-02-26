package com.reflex.reflex.annotationdemo.test;


import com.reflex.reflex.annotationdemo.ProxyClass;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/9 13:36
 */
@ProxyClass(value = "Student", handlerClass = MyProxyHandler.class)
public class Student implements StudentIF{

    @Override
    public void study() {
        System.out.println("我爱学习， 学习使我快乐");
    }
}
