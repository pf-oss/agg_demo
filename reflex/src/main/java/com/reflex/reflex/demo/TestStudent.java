package com.reflex.reflex.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestStudent {

    public static void main(String[] args) throws Exception {
        Class c = Class.forName("com.reflex.reflex.demo.Student");

        // 选择带有String类型的参数构造器
        Constructor constructor = c.getConstructor(java.lang.String.class);
        // 构造一个参数值
        Object obj1 = constructor.newInstance("Zero");

        Constructor constructor1 = c.getConstructor(int.class);
        Object object2 = constructor1.newInstance(111);

        // 静态方法调用("方法名称"， "方法参数类型")
        Method method = c.getMethod("method", String.class);
        // 给方法传递参数值
        // 如果是静态方法不需要传递对象，给一个null,不能不写
        method.invoke(null, "黑米");

        // 实例方法调用
        Object object = c.newInstance();
        Method method1 = c.getMethod("method1");
        method1.invoke(object);

        // 调用实例属性，并赋值
        Field field  = c.getField("name");
        field.set(object, "zero");
        System.out.println("实例属性的值: " + field.get(object));

        // 调用静态属性，并赋值
        Field field2 = c.getField("email");
        field2.set(null, "Dave");
        System.out.println(field2.get(null));




    }
}
