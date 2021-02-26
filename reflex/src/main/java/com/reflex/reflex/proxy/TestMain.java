package com.reflex.reflex.proxy;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/7 16:32
 */
public class TestMain {


    public static void main(String[] args) {

        IStudent a = new A();
        IStudent b = new B(a);
        b.method();
    }
}
