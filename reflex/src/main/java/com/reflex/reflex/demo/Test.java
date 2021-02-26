package com.reflex.reflex.demo;


/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/7 9:47
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException {

        // 死写法， 固定死了只能得到Test对象
        Class c = Test.class;

        // 字符串里写的是这个类的全名，也就是包名+类名
        Class c2 = Class.forName("com.reflex.reflex.demo.Test");
        Class c3 = new Test().getClass();

        System.out.println("类名 :" + c2.getName());
        System.out.println("包名：" + c2.getPackage().getName());
        System.out.println("父类的名称：" + c2.getSuperclass().getName());
        System.out.println("此类的路径: " + c2.getResource("Test.class").getPath());
    }

}
