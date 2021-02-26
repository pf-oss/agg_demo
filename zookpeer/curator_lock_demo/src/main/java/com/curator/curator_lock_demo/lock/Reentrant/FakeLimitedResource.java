package com.curator.curator_lock_demo.lock.Reentrant;

/**
 * @Description:  
 * @author: pengfei_yao
 * @create: 2019/11/11 16:16
 */       
public class FakeLimitedResource {

    // 总共250张火车票
    private Integer ticket = 250;

    public void use() throws InterruptedException{
        try {
            System.out.println("火车票还剩 : " + (--ticket) + "张！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
