package com.reflex.reflex.dynamicProxy;


import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/8 9:53
 */
@Slf4j
public class Student implements StudentIF{

    private String name;

    public  Student(String name) {
        this.name = name;
    }

    @Override
    public void study(){
        log.info("实际方法的打印: " + name + "正在学习");
    }
}
