package com.reflex.reflex.pri;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * @Description:  调用私有构造器
 * @author: pengfei_yao
 * @create: 2020/5/7 15:58
 */
@Slf4j
public class TestPrivateDeclaredConstructors {

    public static void main(String[] args) throws Exception{
        Class cls = Class.forName("com.reflex.reflex.pri.Student");
        // 得到Student类里的所有私有的构造器
        Constructor[] constructors = cls.getDeclaredConstructors();
        int num = 0;
        for (Constructor constructor : constructors){
            num++;
        }
        log.info("Student类的构造器数量: " + num);
        System.out.println();


        // 调用空参数的构造器
        Constructor constructor1 = cls.getDeclaredConstructor();
        constructor1.setAccessible(true);
        constructor1.newInstance();

        // 调用String类型的构造器
        Constructor constructor2 = cls.getDeclaredConstructor(String.class);
        constructor2.setAccessible(true);
        constructor2.newInstance("小明");

        // 调用int类型的构造器
        Constructor constructor3 = cls.getDeclaredConstructor(int.class);
        constructor3.setAccessible(true);
        constructor3.newInstance(11);

    }

}
