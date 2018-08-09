package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description 测试 thymeleaf
 *  1.由于SpringBoot 不支持JSP,因此使用了 thymeleaf 代替了传统的动态页面 JSP , thymeleaf 可以动态传参.
 *  2.pom 文件添加 thymeleaf 依赖后,SpringBoot自动注入 thymeleaf ,
 *  在 org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties 中, 定义了classpath:/templates/
 *  @Controller 注解的类方法返回为字符串时,会自动到 templates 目录下查找对应的.html 文件.
 *
 * @since 2018/8/6
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    /**
     * @author Double
     * @Description  http://localhost:8082/SpringBoot/thymeleaf/hello
     *
     *          thymeleaf hello 最基本页面,
     *          通过model 传参到 templates 目录下的hello.html 页面,
     *          页面从里面取值.
     * @param
     * @return java.lang.String
     * @Data 2018/8/7
     */
    @GetMapping("/hello")
    public String test(Model model){
        model.addAttribute("hello","hello world");
        return "thymeleaf/hello";
    }

    @GetMapping("/thymeleaf")
    public String thymeleaf(Map<String , Object> map){
        map.put("id","Double");
        map.put("text","hello world");
        map.put("items", Arrays.asList("apple","banana","strawberry"));
        map.put("productor","Double");
        return "thymeleaf/thymeleaf";
    }

}
