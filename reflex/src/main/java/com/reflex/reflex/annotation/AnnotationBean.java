package com.reflex.reflex.annotation;


/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/8 11:00
 */
public class AnnotationBean {

    @annotationTest1
    public String address;

    @annotationTest1
    public AnnotationBean(){
    }

    @annotationTest1
    public void method(){}

    public void method(@annotationTest1 String name){

    }

}
