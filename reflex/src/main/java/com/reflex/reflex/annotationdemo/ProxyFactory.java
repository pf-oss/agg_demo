package com.reflex.reflex.annotationdemo;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Description:  产生代理对象的工厂
 * @author: pengfei_yao
 * @create: 2020/5/9 11:20
 */
@Slf4j
public class ProxyFactory {

    /**
     * @Description:
     * @param name:
     * @param packageName:
     * @return: java.lang.Object
     */
    public static Object createProxyObject(String name, String packageName){
        // 搜索包名下的所有类
        List<Class<?>> classes = ClassUtil.getClasses(packageName);
        for (Class<?> cls : classes){
            // 判断是否写有接口，并且是否写有ProxyClass注解
            if (!cls.isInterface() && cls.isAnnotationPresent(ProxyClass.class)){
                try{
                    // 把ProxyClass对象拿出来
                    ProxyClass proxyClass = (ProxyClass) cls.getAnnotation(ProxyClass.class);

                    // 判断类名和注解里的类名是否一致
                    if (proxyClass.value().equals(name)){
                        // 是的话创建这个类的对象
                        Object object = cls.newInstance();

                        // 并且把处理类的对象也创建出来
                        Object handlerObj = proxyClass.handlerClass().newInstance();

                        // 并且把处理处理类的对象创建出来
                        Object handlerObg = proxyClass.handlerClass().newInstance();

                        // 返回动态代理对象
                        return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new ProxyHandler(object, handlerObg));
                    }
                }catch (Exception e){
                    e.getMessage();
                }
            }
        }
        return null;
    }


}
