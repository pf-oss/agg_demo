package com.security.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/6 14:15
 */
@Controller
@RequestMapping("/portal")
public class PortalController {

    @RequestMapping("test")
    @ResponseBody
    public String test(HttpServletResponse response) throws IOException {
        return "test";
    }

    @RequestMapping("/login")
    public String login(){
        return "/portal/login";
    }

    @RequestMapping("/index")
    public String index(){
        return "/portal/index";
    }

    @RequestMapping("/hideForm")
    public String hideForm(Model model, String username, String password){
        System.err.println("123"+username);
        System.err.println("123"+password);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "/portal/hideForm";
    }

}
