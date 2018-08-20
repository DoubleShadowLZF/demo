package com.example.demo;

import com.example.demo.redis.component.User;
import com.example.demo.redis.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashSet;
import java.util.Set;

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
    @Autowired
    private ValueOperations<String,Object> valueOperations;
    @Autowired
    private HashOperations<String,String,Object> hashOperations;
    @Autowired
    private ListOperations<String,Object> listOperations;
    @Autowired
    private SetOperations<String,Object> setOperations;
    @Autowired
    private ZSetOperations<String,Object> zSetOperations;

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

    /**
     * @author Double
     * @Description 使用RedisTemplate 存取对象类型
     * @return void
     * @Data 2018/8/19 15:52
     */
    @Test
    public void setAndGetAUser(){
        User user = userService.queryUser(null , new User("Double",23));
        System.out.println("user"+user);
        redisTemplate.opsForValue().set("test:setUser",user);
        Assert.assertEquals(user.getName(),
                ((User)redisTemplate.opsForValue().get("test:setUser")).getName());
    }

    /**
     * @Description 使用 java 对redis的字符串,hash,链表,无序集合,有序集合进行存取操作
     * @param []
     * @return void
     * @Data 2018/8/20 8:02
     * @author Double
     */
    @Test
    public void testTemplates(){
        //字符串操作
        valueOperations.set("test:valueOperations","valueTemplate");
        Assert.assertEquals("valueTemplate",valueOperations.get("test:valueTemplate"));
        //hash操作
        hashOperations.put("test:hashOperations","testHashKey","testHashValue");
        Assert.assertEquals("testHashValue",hashOperations.get("test:hashOperations","testHashKey"));
        //链表操作
        listOperations.leftPush("test:listOperations","testList");
        Assert.assertEquals("testList",listOperations.rightPop("test:listOperations"));
        //无序集合
        setOperations.add("test:setOperations","testSet");
        LinkedHashSet expVal = new LinkedHashSet(1);
        expVal.add("testSet");
        Set<Object> members = setOperations.members("test:setOperations");
//        Assert.assertEquals(expVal,setOperations.members("test:setOperations"));
        Assert.assertArrayEquals(expVal.toArray(),members.toArray());
        //有序集合
        zSetOperations.add("test:zSetOperations","testZSet",1.0);
        Set<Object> range = zSetOperations.range("test:zSetOperations", 0, 1);
        expVal.clear();
        expVal.add("testZSet");
        Assert.assertArrayEquals(expVal.toArray(),range.toArray());

    }
}