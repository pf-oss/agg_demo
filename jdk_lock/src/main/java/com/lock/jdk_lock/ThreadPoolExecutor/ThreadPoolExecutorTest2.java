package com.lock.jdk_lock.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest2 {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), (ThreadFactory) Thread::new, new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(() -> sleep(1000));
        int activeCount = -1;
        int queueSize = -1;
        while (true) {
            if (activeCount != threadPoolExecutor.getActiveCount()
                    || queueSize != threadPoolExecutor.getQueue().size()) {
                System.out.println("活跃线程个数 " + threadPoolExecutor.getActiveCount());
                System.out.println("核心线程个数 " + threadPoolExecutor.getCorePoolSize());
                System.out.println("队列线程个数 " + threadPoolExecutor.getQueue().size());
                System.out.println("最大线程数 " + threadPoolExecutor.getMaximumPoolSize());
                System.out.println("------------------------------------");
                activeCount = threadPoolExecutor.getActiveCount();
                queueSize = threadPoolExecutor.getQueue().size();
            }
        }
    }

    private static void sleep(long value){
        System.out.println(Thread.currentThread().getName() + "线程执行sleep方法");
        try {
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
