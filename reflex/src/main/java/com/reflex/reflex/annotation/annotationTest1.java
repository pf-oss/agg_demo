package com.reflex.reflex.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/8 10:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
public @interface annotationTest1 {

    String value() default "abc";

    // 属性中还可以声明数组，集合，枚举登类型
    String[] data() default {"zero", "123456"};
}
