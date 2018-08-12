package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description 測試JSP頁面
 *      導入war包的步驟：
 *          https://blog.csdn.net/qq_14853889/article/details/80026885
 * @since 2018/8/12
 */
@Controller
public class ServletController {
    @GetMapping("/testJsp")
    public String success(Model model){
        model.addAttribute("param","userName");
        return "success";
    }
}
