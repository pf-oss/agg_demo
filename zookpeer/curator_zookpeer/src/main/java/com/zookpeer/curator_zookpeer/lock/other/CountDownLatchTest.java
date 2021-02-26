package com.zookpeer.curator_zookpeer.lock.other;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(1);
        System.out.println("主线程开始执行.......");
        // 第一个子线程执行
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("子线程 : " + Thread.currentThread().getName() + "执行");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        executorService.shutdown();



        // 第二个子线程执行
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("子线程 : " + Thread.currentThread().getName() + "执行");
                latch.countDown();
            }
        });
        executorService1.shutdown();
        System.out.println("等待两个线程执行完毕.......");
        try{
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");


    }
}
