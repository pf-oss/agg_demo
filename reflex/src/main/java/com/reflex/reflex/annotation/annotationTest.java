package com.reflex.reflex.annotation;


import java.lang.annotation.*;

/**
 * @Description:  测试注解
 * @author: pengfei_yao
 * @create: 2020/5/8 10:50
 */

// 声明这个注解只能写在类上，也可以选择多个参数
@Target(value = ElementType.TYPE)
// 声明这个注解在代码运行时有效
@Retention(RetentionPolicy.RUNTIME)
// 注释是否在子类也有效果
@Inherited
// 文档奏效
@Documented
public @interface annotationTest {

    // 声明一个属性，当使用次注解时需要传递一个值
    String value();
}
