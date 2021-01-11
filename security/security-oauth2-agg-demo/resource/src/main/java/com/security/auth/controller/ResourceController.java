package com.security.auth.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/7 15:11
 */
@RequestMapping("/resource")
public class ResourceController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(String data){
        return "order1"+data;
    }

    @RequestMapping("callBack")
    @ResponseBody
    public String callBack(){
        return "callBack";
    }

    @RequestMapping("/out/login")
    public String outLogin(Model model, String username, String password) throws IOException, ServletException {
        System.err.println("aaa"+username);
        System.err.println("aaa"+password);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "/portal/wait";
    }


}
