package com.security.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/6 14:15
 */
@Controller
@RequestMapping("/portal")
public class PortalController {

    @RequestMapping("/login")
    public String login(){
        return "/portal/login";
    }
}
