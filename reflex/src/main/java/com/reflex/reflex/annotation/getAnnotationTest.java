package com.reflex.reflex.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: getAnnotations方法可以使用属性、方法、构造器、类的反射机制对象调用，得到所有的注解，返回的是Annotation数组对象：
 * @author: pengfei_yao
 * @create: 2020/5/8 11:17
 */       
public class getAnnotationTest {

    public static void main(String[] args) throws Exception{

        Class cls = AnnotationBean.class;
        // 获得构造器上所有的注解并打印出来
        Constructor constructor = cls.getConstructor();
        Annotation[] annotations = constructor.getAnnotations();
        for (Annotation annotation : annotations){
            System.out.println("构造器上的注解: " + annotation);
        }

        // 获得method方法上所有的注解并打印出来
        Method method = cls.getMethod("method");
        Annotation[] annotations1 = method.getAnnotations();
        for (Annotation annotation : annotations1){
            System.out.println("方法上的所有注解：" + annotation);
        }

        // 获得address属性上所有的注解并打印出来
        Field field = cls.getField("address");
        Annotation[] annotations2 = field.getAnnotations();
        for (Annotation annotation : annotations2){
            System.out.println("address属性上的注解: " + annotation);
        }

    }
}
