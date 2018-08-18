package com.example.demo.web.controller;

import com.example.demo.configuration.food.FoodProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/5
 */
@RestController
public class AutoConfigurationConreoller {

    /**
     * @author Double
     * @Description 测试自动配置映射
     * @return java.lang.String
     * @Data 2018/8/5
     */
    @GetMapping("/viewController")
    public String viewController(){
        return "自动配置测试~";
    }

    @PostMapping("/food")
    public String food( FoodProperties food){
        System.out.println("food:/n"+food);
        return "This food is expired.";
    }
}
