package com.lock.jdk_lock.ThreadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  
 * @author: pengfei_yao
 * @create: 2019/12/2 13:45
 */       
public class ThreadLocalTest2 {

    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "初始化......");
    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException{

        Thread thread1 = new Thread(() -> {
           threadLocal.set("thread t1");
           try{
               TimeUnit.MICROSECONDS.sleep(random.nextInt(1000));
               System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            threadLocal.set("thread t2");
            try{
                TimeUnit.MICROSECONDS.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }, "thread2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());

    }


}

