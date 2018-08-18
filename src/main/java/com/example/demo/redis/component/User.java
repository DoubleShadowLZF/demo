package com.example.demo.redis.component;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String name ;
    private Integer age ;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
