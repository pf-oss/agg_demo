package com.curator.curator_count_demo.share_count;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DistributedAtomicLongExample {

    private static final int CLIENT_COUNT = 5;

    private static final String PATH = "/examples/counter";

    // 会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;

    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;

    // Zookeeper服务地址
    private static final String CONNECT_ADDR = "127.0.0.1:2181";

    public static void main(String[] args) {
        try {

            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString(CONNECT_ADDR)
                    .connectionTimeoutMs(CONNECTION_TIMEOUT)
                    .sessionTimeoutMs(SESSION_TIMEOUT)
                    .retryPolicy(retryPolicy)
                    .build();
            client.start();

            List<DistributedAtomicLong> examples = new ArrayList<>();
            ExecutorService service = Executors.newFixedThreadPool(CLIENT_COUNT);
            for (int i = 0; i < CLIENT_COUNT; ++i){
                final DistributedAtomicLong count = new DistributedAtomicLong(client, PATH, new RetryNTimes(10, 10));
                examples.add(count);

                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        AtomicValue<Long> value = count.increment();
                        System.out.println("操作是否成功：" + value.succeeded());
                        if (value.succeeded()){
                            System.out.println("操作成功：操作前的值为：" + value.preValue() + "操作后的值为：" + value.postValue());
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
