package com.async.asyncdemo.service;

import com.async.asyncdemo.config.AsyncConfig;
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

    @Async(AsyncConfig.EXECUTOR_ONE_BEAN_NAME)
    public Integer execute01() {
        log.info("[execute01]");
        return 1;
    }

    @Async(AsyncConfig.EXECUTOR_TWO_BEAN_NAME)
    public Integer execute02() {
        log.info("[execute02]");
        return 2;
    }



}
