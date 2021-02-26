package com.curator.curator_lock_demo.lock.Reentrant;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  客户端
 * @author: pengfei_yao
 * @create: 2019/11/11 16:39
 */
public class LockingExample {

    private static final int QTY = 5;

    private static final int REPETITIONS = QTY * 10;

    private static final String CONNECTION_STRING = "127.0.0.1:2181";

    private static final String PATH = "/examples/locks";

    public static void main(String args[]){

        final FakeLimitedResource resource = new FakeLimitedResource();

        ExecutorService service = Executors.newFixedThreadPool(QTY);

        try {

            for (int i = 0; i < QTY; ++i){
                final int index = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECTION_STRING, new ExponentialBackoffRetry(1000, 3, Integer.MAX_VALUE));
                        try {
                            client.start();
                            ExampleClentThatLocks exampleClentThatLocks = new ExampleClentThatLocks(client, PATH, resource, "Client " + index);
                            for (int j = 0; j < REPETITIONS; ++j){
                                exampleClentThatLocks.doWork(10, TimeUnit.SECONDS);
                            }
                        }catch (InterruptedException e){
                            Thread.currentThread().interrupt();
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            CloseableUtils.closeQuietly(client);
                        }
                        return null;
                    }
                };
                service.submit(task);
            }
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
