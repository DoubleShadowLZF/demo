package com.example.demo.crud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/10
 */
@Controller
public class LoginController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("/login")
    public String login(String userName , String password , Map<String , String> result , HttpSession session){
        log.debug("userName:"+userName +";passwrod:"+password);
        if(!StringUtils.isEmpty(userName) && "Double".equals(password)){
            session.setAttribute("userName" , userName);
            log.debug("登陸成功。。。");
            //如果直接使用 index 跳轉到 index.html 頁面，從新刷新頁面時，可能造成表單重複提交的問題，使用轉發可以解決該問題。
//            return "dashboard";
            result.put("message","登陸成功...");
            return "redirect:/dashboard.html";
        }
        result.put("message" , "登陸密碼錯誤...");
        log.debug("登陸失敗。。。");
        //如果直接使用 index 跳轉到 index.html 頁面，從新刷新頁面時，可能造成表單重複提交的問題，使用轉發可以解決該問題。
        return  "index";
    }

}
