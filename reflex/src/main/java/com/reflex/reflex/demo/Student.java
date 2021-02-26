package com.reflex.reflex.demo;

public class Student {

    public String name;
    public static String email;

    public Student(String name){
        System.out.println("构建对象String:" + name);
    }

    public Student(int age){
        System.out.println("构建对象int: " + age);
    }

    public Student(){

    }

    public static  void method(){
        System.out.println("static method");
    }

    public static void method(String name){
        System.out.println("static Method：: " + name);
    }

    public void method1(){
        System.out.println("Object Method");
    }
}
