package com.lucky.NewSignInWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/main")
public class MainController {

    @GetMapping("/count.do")
    public void getCount() {
        System.out.println("count.do");
    }

    @GetMapping("/rank.do")
    public void getRank() {}
}
