package com.example.demo.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.component.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/testRedis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @PostMapping("/string")
    public String setStringRedis(@RequestBody JSONObject reqBody){
        ValueOperations<String, String> svo = stringRedisTemplate.opsForValue();
        Set<Map.Entry<String, Object>> entries = reqBody.entrySet();
        for(Map.Entry<String, Object> entry : entries){
            svo.set(entry.getKey(), (String) entry.getValue());
        }
        return "SUCCESS";
    }

    @GetMapping("/string/{key}")
    public String getStringRedis(@PathVariable("key")String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping
    public String testUrl(){
        return "SUCCESS";
    }
}
