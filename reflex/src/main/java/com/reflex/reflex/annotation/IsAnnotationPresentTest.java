package com.reflex.reflex.annotation;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:  isAnnotationPresent方法可以判断属性、方法、构造器、类是否写有某个注解
 * @author: pengfei_yao
 * @create: 2020/5/8 11:07
 */
public class IsAnnotationPresentTest {

    public static void main(String[] args) throws Exception{
        Class cls = AnnotationBean.class;

        // 判断构造器上有没有写@annotationTest1注解
        Constructor constructor = cls.getConstructor();
        System.out.println(constructor.isAnnotationPresent(annotationTest1.class));

        // 判断method方法上有没有写@annotationTest1注解
        Method method = cls.getMethod("method");
        System.out.println(method.isAnnotationPresent(annotationTest1.class));

        // 判断address属性上有没有写annotationTest1注解
        Field field = cls.getField("address");
        System.out.println(field.isAnnotationPresent(annotationTest1.class));
    }
}
