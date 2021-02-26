package com.curator.curator_lock_demo.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

public class ordinary {

    public static void main(String arg[]) throws Exception{

        CountDownLatch downLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("downLatch1 = " + downLatch.toString());
//                        downLatch.await();
                        sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = simpleDateFormat.format(new Date());
                    System.out.println("生成的订单号是 : " + orderNo);
                }
            }).start();
        }
        System.out.println("downLatch2 = " + downLatch.toString());
//        downLatch.countDown();
    }
}
