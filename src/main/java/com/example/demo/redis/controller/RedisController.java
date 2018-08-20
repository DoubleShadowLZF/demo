package com.example.demo.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.redis.component.User;
import com.example.demo.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Double
 * @Description Redis Controller
 * @Data 2018/8/19 21:50
 */
@RestController
@RequestMapping("/testRedis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/user")
    public String insertUser(@RequestBody User user){
        userService.insertUser(user);
        return "SUCCESS";
    }

    @PutMapping("/user/{userId}")
    public String updateUser(@RequestBody User user,@PathVariable("userId") Integer userId){
        userService.updateUser(userId,user);
        return "SUCCESS";
    }

    @GetMapping("/user")
    public Map<Integer,User> queryList(){
        return userService.list();
    }

    @GetMapping("/user/{userId}")
    public User queryUser(@PathVariable("userId") Integer userId){
        return userService.queryUser(userId,null);
    }

    /**
     * @author Double
     * @Description 共享session
     * @return java.lang.String
     * @Data 2018/8/19 15:58
     */
    @GetMapping("/uuid")
    public String uid(HttpSession session){
        UUID uuid = (UUID) session.getAttribute("uuid");
        if(uuid == null){
            uuid = java.util.UUID.randomUUID();
        }
        session.setAttribute("uuid",uuid);
        return session.getId();
    }

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
