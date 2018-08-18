package com.example.demo;

import com.example.demo.redis.component.User;
import com.example.demo.component.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * redis 测试类
 * 1.在配置文件application.yml 配置属性
 * 2.如果取出的数据是对象,需要序列化对象
 * 3.使用 SpringBoot 的模板对象 RedisTemplate 进行 redis 操作
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * @author Double
     * @Description 使用redisTemplate 存取字符串
     * @return void
     * @Data 2018/8/19 3:47
     */
    @Test
    public void setAndGet(){
        redisTemplate.opsForValue().set("test:set","testValue");
        Assert.assertEquals("testValue",redisTemplate.opsForValue().get("test:set"));
    }

    @Test
    public void setAndGetAUser(){
        User user = userService.getUser();
        System.out.println("user"+user);
        redisTemplate.opsForValue().set("test:setUser",user);
        Assert.assertEquals(user.getName(),
                ((User)redisTemplate.opsForValue().get("test:setUser")).getName());
    }
}
