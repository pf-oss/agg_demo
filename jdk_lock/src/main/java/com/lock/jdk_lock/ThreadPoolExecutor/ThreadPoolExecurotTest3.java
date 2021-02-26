package com.lock.jdk_lock.ThreadPoolExecutor;


import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecurotTest3 {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2), (ThreadFactory) Thread::new, new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.execute(new shortTask());
        threadPoolExecutor.execute(new longTask());
        threadPoolExecutor.execute(new longTask());
        threadPoolExecutor.execute(new shortTask());

        // 马上关闭，并返回还未被执行的任务
//        List<Runnable> runnables = threadPoolExecutor.shutdownNow();
        threadPoolExecutor.shutdown();
        boolean isShutDown = false;
        try {
            isShutDown = threadPoolExecutor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isShutDown){
            System.out.println("线程池再3秒内成功");
        }

//        System.out.println(runnables);
        System.out.println("已经执行了像城池shutdownNow 方法");
    }

    static class shortTask implements Runnable{
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "执行shortTask完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("shourtTask执行过程中被打断" + e.getMessage());
            }
        }
    }

    static class longTask implements Runnable{

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "执行longTask完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("shourtTask执行过程中被打断" + e.getMessage());
            }
        }
    }





}
