package com.zookpeer.curator_zookpeer.lock.notretryIn;


import com.zookpeer.curator_zookpeer.lock.interprocessmutex.FakeLimitedResource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2019/11/21 16:27
 */
public class ExampleClientThatLocks {

    /**
     *  不可重入锁
     */
    private final InterProcessSemaphoreMutex lock;

    /**
     *  共享资源
     */
    private final FakeLimitedResource resource;

    /**
     *   客户端名称
     */
    private final String clientName;

    public ExampleClientThatLocks(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName){

        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessSemaphoreMutex(client, lockPath);
    }

    public void doWork(long time, TimeUnit unit)throws Exception{
        if (!lock.acquire(time, unit)){
            throw new IllegalStateException(clientName + "could not acquire the lock");
        }
        System.out.println(clientName + " has the lock");

        if (!lock.acquire(time, unit)){
            throw new IllegalStateException(clientName + " could not acquire the lock");
        }
        System.out.println(clientName + "has the lock again");

        try{
            // 操作资源
            resource.use();
        }finally {
            System.out.println(clientName + "releasing the lock");
            lock.release();
            // 调用两次acquire释放两次
            lock.release();
        }
    }



}
