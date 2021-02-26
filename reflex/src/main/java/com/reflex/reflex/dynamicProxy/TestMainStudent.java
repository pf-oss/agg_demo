package com.reflex.reflex.dynamicProxy;


import java.lang.reflect.Proxy;

/**
 * @Description:  测试方法
 * @author: pengfei_yao
 * @create: 2020/5/8 10:08
 */
public class TestMainStudent {

    public static void main(String[] args) {
        // 构建需要代理的对象
        StudentIF student = new Student("小明");
        // 得到需要代理的对象的class对象
        Class cls = student.getClass();
        // 把代理对象传递到处理类里
        ProxyHandler handler = new ProxyHandler(student);
        // 使用Proxy调用生产代理类方法，这个方法会生产一个动态代理对象
        student = (StudentIF) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), handler);

        student.study();
    }
}
