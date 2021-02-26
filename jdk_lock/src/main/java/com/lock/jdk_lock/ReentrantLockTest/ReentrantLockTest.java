package com.lock.jdk_lock.ReentrantLockTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @Description:  
 * @author: pengfei_yao
 * @create: 2019/12/2 14:27
 */       
public class ReentrantLockTest {
    private static  final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        IntStream.range(0, 2).forEach(i -> new Thread(ReentrantLockTest :: needLock).start());
    }

    public static void needLock(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "开始工作");
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
