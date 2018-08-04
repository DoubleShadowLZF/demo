package com.example.demo.controller;

import com.example.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/4
 */
@RestController
public class PropertityController {

    @Autowired
    Person person ;

    @GetMapping("/personObejct")
    public Object personObejct(){
        return person ;
    }

    @GetMapping("/person")
    public Person person(){
        return person ;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }


}
