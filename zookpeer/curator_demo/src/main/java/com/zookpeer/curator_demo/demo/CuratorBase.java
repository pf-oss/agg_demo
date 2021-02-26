package com.zookpeer.curator_demo.demo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @Description:  curator基本用法
 * @author: pengfei_yao
 * @create: 2019/11/8 14:11
 */
public class CuratorBase {

    // 会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;

    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;

    // Zookeeper服务地址
    private static final String CONNECT_ADDR = "127.0.0.1:2181";

    // 创建连接实例
//    private CuratorFramework client = null;

    public static void main(String[] args) throws Exception{

        // 1. 重试策略: 初试时间为1s，重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        // 2. 通过工厂创建连接
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
//                .namespace("root")
                .build();

        // 3. 开启连接
        client.start();

        System.out.println("ZooKeeper states : " + ZooKeeper.States.CONNECTED);
        System.out.println("客户端连接状态 : " + client.getState());
        System.out.println("客户端 : " + client.getZookeeperClient().toString());

        // 创建永久节点
    //    client.create().forPath("/curator", "/curator data".getBytes());

        // 创建永久有序节点
        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/curator_sequential", "/curator_sequential data".getBytes());

        // 创建临时节点
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/curator/ephemeral", "/curator/ephemeral data".getBytes());

        // 创建临时有序节点
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/curator/ephemeral_path1", "/curator/ephemeral_path1 data".getBytes());

        //
        client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/curator/ephemeral_path2", "curator/ephemeral_path2 data".getBytes());

        // 测试检查某个节点是否存在
        Stat stat1 = client.checkExists().forPath("/curator");
        Stat stat2 = client.checkExists().forPath("/curator2");

        System.out.println("'/curator'是否存在: " + (stat1 != null ? true : false));
        System.out.println("'/curator2'是否存在 :" + (stat2 != null ? true :false));

        // 获取某个节点的所有节点
        System.out.println("所有的节点 : " + client.getChildren().forPath("/"));

        // 获取某个节点的数据
        System.out.println("获取某个节点的数据 : " + new String(client.getData().forPath("/curator")));

        // 设置某个节点数据
        client.setData().forPath("/curator", "/curator modified data".getBytes());

        System.out.println("获取新设置节点的数据 : " + new String(client.getData().forPath("/curator")));

        // 创建测试节点数据
        client.create().orSetData().creatingParentContainersIfNeeded().forPath("/curator/del_key1", "/curator/del_key1 data".getBytes());

        client.create().orSetData().creatingParentContainersIfNeeded().forPath("/curator/del_key2", "/curator/del_key2 data".getBytes());

        client.create().orSetData().creatingParentContainersIfNeeded().forPath("/curator/del_key2/test_key", "/curator/del_key2 data".getBytes());


        // 删除节点
        client.delete().forPath("/curator/del_key1");

        // 级联删除子节点
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/curator/del_key2");
    }

}
