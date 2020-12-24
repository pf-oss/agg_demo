package com.log.logback.controller;

// DemoController.java

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description:
 * @author: pf
 * @create: 2020/12/17 16:53
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

//    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/debug")
    public void debug() {
        log.debug("debug");
    }

    @GetMapping("/info")
    public void info() {
        log.info("info");
    }

    @GetMapping("/error")
    public void error() {
        log.error("error");
    }

}
