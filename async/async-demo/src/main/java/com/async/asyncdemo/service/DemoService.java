package com.async.asyncdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/15 14:02
 */
@Service
@Slf4j
public class DemoService {


    public Integer execute01() {
        log.info("[execute01]");
        sleep(10);
        log.info("[execute01.............]");
        return 1;
    }

    public Integer execute02() {
        log.info("[execute02]");
        sleep(5);
        log.info("[execute02.............]");
        return 2;
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Async
    public Integer execute01Async() {
        return this.execute01();
    }

    @Async
    public Integer execute02Async() {
        return this.execute02();
    }



    @Async
    public Future<Integer> execute01AsyncWithFuture() {
        return AsyncResult.forValue(this.execute01());
    }

    @Async
    public Future<Integer> execute02AsyncWithFuture() {
        return AsyncResult.forValue(this.execute02());
    }


    // DemoService.java

    @Async
    public ListenableFuture<Integer> execute01AsyncWithListenableFuture() {
        try {
            return AsyncResult.forValue(this.execute02());
        } catch (Throwable ex) {
            return AsyncResult.forExecutionException(ex);
        }
    }

    // DemoService.java

    @Async
    public Integer zhaoDaoNvPengYou(Integer a, Integer b) {
        throw new RuntimeException("程序员不需要女朋友");
    }


}
