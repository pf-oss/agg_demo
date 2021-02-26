package com.reflex.reflex.annotationdemo;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:  注解类，声明一些需要配置的信息
 * @author: pengfei_yao
 * @create: 2020/5/9 10:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProxyClass {

    // 使用此注解必须配置类名信息
    public String value();

    // 使用此注解必须配置处理类的Class对象
    public Class handlerClass();

    // 配置不需要过滤的方法名信息
    public String methodName() default "";

}
