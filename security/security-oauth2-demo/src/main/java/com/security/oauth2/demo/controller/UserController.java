package com.security.oauth2.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/6 14:15
 */
@RestController
@RequestMapping("/account")
public class UserController {

    @GetMapping("/user")
    public String getUser(){
        return "admin";
    }

    @GetMapping("/callback")
    public String callback(){
        return "授权码回调测试";
    }

    @GetMapping("/password")
    public String getServer(){
        return "密码模式测试";
    }

    @GetMapping("/codeTest")
    public String authCode(){
        return "授权码测试";
    }
}
