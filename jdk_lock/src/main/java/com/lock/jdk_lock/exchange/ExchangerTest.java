package com.lock.jdk_lock.exchange;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description:  参考: https://mrbird.cc/JUC-Exchanger.html
 * @author: pengfei_yao
 * @create: 2019/12/2 10:59
 *
 * exchange方法用于向另一个线程发送数据，方法的返回值为另一个线程发送过来的数据
 */       
public class ExchangerTest {

    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            System.out.println("thread1 开始");
            try{
                String exchange = exchanger.exchange("来自thread1的数据", 5, TimeUnit.SECONDS);
                System.out.println("接收thread2发送的数据: " + exchange);
            }catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 结束");
        }, "thread1").start();

        new Thread(() -> {
            System.out.println("thread2 开始");
            try{
                // thread1会进入等待
//                TimeUnit.SECONDS.sleep(3);
                String exchange = exchanger.exchange("来自thread2的数据", 5, TimeUnit.SECONDS);
                System.out.println("接收thread1发送的数据: " + exchange);
            }catch (InterruptedException  | TimeoutException e){
                e.printStackTrace();
            }
            System.out.println("thread2结束");
        }, "thread2").start();


        new Thread(() -> {
            System.out.println("thread3 开始");
            try{
                String exchange = exchanger.exchange("来自thread3的数据", 5, TimeUnit.SECONDS);
                System.out.println("thread3 接收到的数据 : " + exchange);
            }catch (InterruptedException | TimeoutException e){
                e.printStackTrace();
            }
        }, "thread3").start();


    }
}
