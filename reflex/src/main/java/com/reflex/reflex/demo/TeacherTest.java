package com.reflex.reflex.demo;

import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/5/7 11:46
 */
public class TeacherTest {

    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("E:\\20200323\\demo\\reflex\\src\\main\\java\\com\\reflex\\reflex\\demo\\p.txt"));

        Class teachercls = Class.forName("com.reflex.reflex.demo.Teacher");
        Object obj = teachercls.newInstance();
        Set<Object> keys = properties.keySet();
        System.out.println("keys ==" + JSON.toJSONString(keys));

        for (Object object : keys){
            try{
                String name = object.toString();
                name = "set" + (name.charAt(0) + "").toUpperCase() + name.substring(1);
                Method method = teachercls.getMethod(name, String.class);
                method.invoke(obj, properties.getProperty(object.toString()));
                System.out.println("obj == " + obj);
            }catch (Exception e){
                e.getMessage();
            }
        }
        Teacher teacher = (Teacher) obj;
        System.out.println("teacher==="+ JSON.toJSONString(teacher));
    }
}
