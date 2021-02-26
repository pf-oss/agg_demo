package com.reflex.reflex.pri;


import lombok.Data;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/7 14:41
 */
@Data
public class Student {

    public String name;

    public String address;

    public String email;

    public String phoneNumber;

    public String country;

    private int age;

    private String sex;

    private String school;

    private String cls;


    private void method(){
        System.out.println("调用的是空参数的method");
    }

    private void method(String name){
        System.out.println("String类型参数的method方法，参数值为: " + name);
    }

    private void method2(String str){
        System.out.println(str);
    }

    private void method3(){
        System.out.println("调用的是method3");
    }


    private Student(){
        System.out.println("调用的是空参的构造器");
    }

    private Student(String name){
        System.out.println("调用的是String类型参数的构造器， 参数的值为:" + name);
    }

    private Student(int age){
        System.out.println("调用的是int类型参数的构造器，参数的值为：" + age);
    }

}
