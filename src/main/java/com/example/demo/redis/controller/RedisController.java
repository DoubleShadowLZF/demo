package com.example.demo.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.redis.component.User;
import com.example.demo.redis.service.BaseUserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/testRedis")
public class RedisController {

    /*@Autowired
    private StringRedisTemplate stringRedisTemplate;*/

    @Autowired
    private BaseUserService userService;

    /*@Autowired
    private RedisTemplate redisTemplate;*/

    @PostMapping("/user")
    public String insertUser(@RequestBody User user){
        return userService.insertUser(user)== 0 ? "缓存中已存在" : "添加成功.";
    }

    @PutMapping("/user/{id}")
    public String updateUser(@RequestBody User user,@PathVariable("id") Integer userId){
        user.setId(userId);
        userService.updateUser(userId,user);
        return "SUCCESS";
    }

    @GetMapping("/user")
    public Map<Integer,User> queryList(){
        return userService.list();
    }

    @GetMapping("/user/{userId}")
    public User queryUser(@PathVariable("userId") Integer userId){
        User u = new User();
        u.setId(userId);
        User user = userService.queryUser(u);
log.debug(">>RedisController:"+user);
        return user;
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
        /*ValueOperations<String, String> svo = stringRedisTemplate.opsForValue();
        Set<Map.Entry<String, Object>> entries = reqBody.entrySet();
        for(Map.Entry<String, Object> entry : entries){
            svo.set(entry.getKey(), (String) entry.getValue());
        }*/
        return "SUCCESS";
    }

    @GetMapping("/string/{key}")
    public String getStringRedis(@PathVariable("key")String key){
        return null; //stringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping
    public String testUrl(){
        return "SUCCESS";
    }
}
