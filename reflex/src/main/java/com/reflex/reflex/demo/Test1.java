package com.reflex.reflex.demo;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Test1 extends Thread implements Runnable{


    public static void main(String[] args) throws ClassNotFoundException{
        Class classes = Class.forName("com.reflex.reflex.demo.Test1");


        // 接口获得
        Class[] cs = classes.getInterfaces();
        for (Class class1 : cs){
            System.out.println("接口名" + class1.getName());
        }

        // 方法获得
        Method[] methods = classes.getMethods();
        for (Method method2 : methods){
            System.out.println("方法名: " + method2.getName());
            // 得到方法里需要传递的参数
            System.out.println("方法里需要传递的参数: " + Arrays.toString(method2.getParameterTypes()));
            System.out.println("得到方法返回的类型：" + method2.getReturnType());
            System.out.println("得到方法方法的返回类型： " + method2.getExceptionTypes());
            System.out.println("==============================================");
        }

        // 构造器获得
        Constructor[] cc = classes.getConstructors();
        for (Constructor constructor : cc){
            System.out.println("构造参数传递的参数类型："+ JSON.toJSONString(constructor.getParameterTypes()));
        }

        // 属性获得
        Field[] fs = classes.getFields();
        for (Field field : fs){
            System.out.println(field.getName() + " " + field.getType());
        }


    }
}
