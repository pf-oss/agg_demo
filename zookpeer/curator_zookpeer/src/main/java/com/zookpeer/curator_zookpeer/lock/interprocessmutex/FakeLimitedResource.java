package com.zookpeer.curator_zookpeer.lock.interprocessmutex;

/**
 * @Description: 共享资源（可重入锁）
 * @author: pengfei_yao
 * @create: 2019/11/21 15:27
 */
public class FakeLimitedResource {

    /**
     * 总共250张火车票
     */
    private Integer ticket = 250;

    public void use() throws InterruptedException{
        try{
            System.out.println("火车票还剩"+(--ticket) + "张！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
