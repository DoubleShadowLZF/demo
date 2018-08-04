package com.example.demo.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/4
 */
@Component
@PropertySource(value = {"classpath:person.properties"})
//@ImportResource(locations = "classpath:person.properties")
@ConfigurationProperties(prefix="person")
@ToString
@Validated
public class Person {
//    @Value("${person.name}")
    private String name;
    private Integer age ;
    @Email
//    @Value("${person.email}")
    private String email ;
    private List role;
    private Map<String , String> backpack ;
    private Map<String , String > food ;

    public Map<String, String> getFood() {
        return food;
    }

    public void setFood(Map<String, String> food) {
        this.food = food;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List getRole() {
        return role;
    }

    public void setRole(List role) {
        this.role = role;
    }

    public Map<String, String> getBackpack() {
        return backpack;
    }

    public void setBackpack(Map<String, String> backpack) {
        this.backpack = backpack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
