package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/6
 */
@Controller
public class ThymeleafController {

    @GetMapping("/thymeleaf")
    public String test(Model model){
        model.addAttribute("hello","hello world");
        return "thymeleaf/hello";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
