package com.zookpeer.curator_demo.demo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestTransaction {


    // 会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;

    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;

    // Zookeeper服务地址
    private static final String CONNECT_ADDR = "127.0.0.1:2181";

    public static void main(String args[]) throws Exception{

        // 1. 重试策略：初始时间为1s， 重试10s
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        // 2. 通过工厂创建连接
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
                .build();

        // 3. 开启连接
        client.start();

        // 定义几个基本操作
        CuratorOp curatorOp = client.transactionOp().create()
                .forPath("/curator/one_path", "some data".getBytes());

        CuratorOp setDataOp = client.transactionOp().setData()
                .forPath("/curator", "other data".getBytes());

        CuratorOp deleteOp = client.transactionOp().delete()
                .forPath("/curator");

//        // 事务执行结果
//        List<CuratorTransactionResult> results = client.transaction()
//                .forOperations(curatorOp, setDataOp, deleteOp);

        // 遍历输出结果
//        for (CuratorTransactionResult result : results){
//            System.out.println("执行结果是 : " + result.getForPath() + "--" + result.getType());
//        }


//        client.create().forPath("/zk-huey/cnode","/curator data".getBytes());

        /**
         *  在注册监听的时候， 如果传入此参数，当时间触发时， 逻辑由线程池处理
         */
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // 监听数据节点的变化情况
        final NodeCache nodeCache = new NodeCache(client, "/zk-huey/cnode", false);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(
                new NodeCacheListener() {
                    @Override
                    public void nodeChanged() throws Exception {
                        System.out.println("Node data is changed, new data: " + new String(nodeCache.getCurrentData().getData()));
                    }
                }, pool
        );


        /**
         *  监听子节点的变化请款
         */
        final PathChildrenCache childrenCache = new PathChildrenCache(client, "/zk-huey", true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener(
                new PathChildrenCacheListener() {
                    @Override
                    public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                        switch (event.getType()){
                            case CHILD_ADDED:
                                System.out.println("CHILD_ADDED: " + event.getData().getPath());
                                break;
                            case CHILD_REMOVED:
                                System.out.println("CHILD_REMOVED: " + event.getData().getPath());
                                break;
                            case CHILD_UPDATED:
                                System.out.println("CHILD_UPDATE: " + event.getData().getPath());
                                break;
                            default:
                                break;
                        }
                    }
                }, pool
        );
//        client.create().forPath("/zk-huey/cnode","/curator data".getBytes());
        client.setData().forPath("/zk-huey/cnode", "world51".getBytes());
        Thread.sleep(10 * 1000);
        pool.shutdown();
        client.close();

    }
}
