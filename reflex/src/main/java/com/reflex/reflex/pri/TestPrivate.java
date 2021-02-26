package com.reflex.reflex.pri;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @Description:  反射获取私有属性
 * @author: pengfei_yao
 * @create: 2020/5/7 14:45
 */
@Slf4j
public class TestPrivate {

    public static void main(String[] args)throws Exception {
        Class cls = Class.forName("com.reflex.reflex.pri.Student");

        Student student = (Student)cls.newInstance();

        // 获得Student类里的所有私有属性
        Field[] fields = cls.getDeclaredFields();
        log.info("Student类里所有的私有属性");
        for (Field field : fields){
            // 进行私有属性的操作的授权
            field.setAccessible(true);
            log.info(field.getName());
        }
        System.out.println();

        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        log.info("调用的属性名称" + field.getName());
        log.info("调用的属性名称：" + field.getName());
        // 属性赋值
        field.set(student, "张全蛋");
        // 打印属性的值
        log.info("name属性的值" + student);
    }

}
