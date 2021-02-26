package com.lock.jdk_lock.ThreadLoclMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2019/12/2 14:00
 */
public class MyThreadLocl<T> {

    private final Map<Thread, T> threadTMap = new HashMap<>();

    public void set(T t){
        synchronized (this){
            Thread key = Thread.currentThread();
            threadTMap.put(key, t);
        }
    }
    public T get(){
        synchronized (this){
            Thread key = Thread.currentThread();
            T t = threadTMap.get(key);
            if (t == null){
                return initalValue();
            }else {
                return t;
            }
        }
    }
    public T initalValue(){
        return null;

    }

}
