package com.reflex.reflex.proxy;

/**
 * @Description:  代理类
 * @author: pengfei_yao
 * @create: ] 16:29
 */
public class B implements IStudent{

    private IStudent iStudent = null;
    public B(IStudent iStudent){
        this.iStudent = iStudent;
    }

    @Override
    public void method() {
        System.out.println("方法调用之前的打印");
        iStudent.method();
        System.out.println("方法调用之后的打印");
    }
}
