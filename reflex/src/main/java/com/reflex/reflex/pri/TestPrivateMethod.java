package com.reflex.reflex.pri;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/7 15:44
 */
@Slf4j
public class TestPrivateMethod {

    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("com.reflex.reflex.pri.Student");
        Student stu = (Student) cls.newInstance();

        // 获得Student类里的所有私有方法
        Method[] methods = cls.getDeclaredMethods();
        log.info("Student类里所有的私有方法：");
        for (Method method : methods){
            log.info(method.getName());
        }
        System.out.println();
        // 获得Student类里带有String类型参数的method方法
        Method method = cls.getDeclaredMethod("method", String.class);
        method.setAccessible(true);
        method.invoke(stu, "小明");

        // 获得Student类里空参数的method方法
        Method method2 = cls.getDeclaredMethod("method");
        method2.setAccessible(true);
        method2.invoke(stu);
    }
}
