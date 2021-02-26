package com.reflex.reflex.annotationdemo.test;


import com.reflex.reflex.annotationdemo.ProxyFactory;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/9 13:57
 */
public class TestMain {

    public static void main(String[] args) throws Exception{

        // 只需要写生成动态代理类和方法调用代码即可
        StudentIF studentIF = (StudentIF) ProxyFactory.createProxyObject("Student", "zhujie");
        studentIF.study();
        System.out.println(studentIF.toString());
    }
}
