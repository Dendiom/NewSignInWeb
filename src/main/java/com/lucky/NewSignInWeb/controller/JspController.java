package com.lucky.NewSignInWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 专门写一个controller来实现所有浏览器访问页面的jsp跳转，不知道这样写对不对，
 * 查了很久资料也没找到很好的解释，springboot不支持直接url访问jsp。
 */
@Controller
public class JspController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("get login");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/perfect")
    public String perfect() {
        return "perfect";
    }

    @GetMapping("/main/form")
    public String form() {
        return "main/form";
    }
}
