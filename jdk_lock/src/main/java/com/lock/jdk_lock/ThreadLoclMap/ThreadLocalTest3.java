package com.lock.jdk_lock.ThreadLoclMap;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2019/12/2 14:13
 */
public class ThreadLocalTest3 {
    private static MyThreadLocl<String> threadLocl = new MyThreadLocl<String>(){
        @Override
        public String initalValue(){
            return "initalValue";
        }};
    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException{
        Thread thread1 = new Thread( ()->{
            try{
                TimeUnit.MICROSECONDS.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocl.get());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
           try{
               TimeUnit.MICROSECONDS.sleep(random.nextInt(10000));
               System.out.println(Thread.currentThread().getName() + " " + threadLocl.get());
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        }, "thread2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
