package com.example.demo.redis.service;

import com.example.demo.redis.component.User;

import java.util.Map;
/**
 * @Description 模拟数据增删改查操作
 * @Data 2018/9/5
 * @author Double
 */
public abstract class BaseUserService {
    public abstract User queryUser(User user);

    public abstract int insertUser(User user);

    public abstract int updateUser(Integer userId, User user);

    public abstract Map<Integer, User> list();
}
