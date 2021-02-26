package com.zookpeer.curator_zookpeer.watcher;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Description:  PathChildrenCache用于监听子节点的变化(不能监听二级节点)
 * @author: pengfei_yao
 * @create: 2019/11/19 16:19
 */       
public class PathChildrenCache_Sample {

    static String path = "/zk-book2";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
            .sessionTimeoutMs(60000)
            .connectionTimeoutMs(15000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        PathChildrenCache cache = new PathChildrenCache(client, path, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()){
                    case CHILD_ADDED:
                        System.out.println("=====> CHILD_ADDED : " + event.getData().getPath() + " 数据: " + new String(event.getData().getData()));
                        break;
                    case CHILD_REMOVED:
                        System.out.println("=====> CHILD_REMOVED : " + event.getData().getPath() + " 数据: " + new String(event.getData().getData()));
                        break;
                    case CHILD_UPDATED:
                        System.out.println("=====> CHILD_UPDATED : " + event.getData().getPath() + " 数据: " + new String(event.getData().getData()));
                        break;
                    default:
                        break;
                }
            }
        });

        client.create().withMode(CreateMode.PERSISTENT).forPath(path, "init".getBytes());
        Thread.sleep(1000);
        client.create().withMode(CreateMode.PERSISTENT).forPath(path+"/c1");
        Thread.sleep(1000);
        client.setData().forPath(path + "/c1", "I LOVE YOU".getBytes());
        Thread.sleep(1000);
        client.delete().forPath(path + "/c1");
        Thread.sleep(1000);
        client.delete().forPath(path);
        Thread.sleep(10000);
        cache.close();
        client.close();

    }
}
