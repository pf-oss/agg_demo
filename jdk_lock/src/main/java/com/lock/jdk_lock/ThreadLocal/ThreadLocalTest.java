package com.lock.jdk_lock.ThreadLocal;

/**
 * @Description: https://mrbird.cc/ThreadLocal.html
 * @author: pengfei_yao
 * @create: 2019/12/2 13:35
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "初始值");

    public static void main(String[] args) {
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }

}
