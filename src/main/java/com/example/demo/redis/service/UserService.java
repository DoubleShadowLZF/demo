package com.example.demo.redis.service;

import com.example.demo.redis.component.User;

import java.util.Map;

public abstract class UserService {
    public abstract User queryUser(Integer userId , User user);

    public abstract int insertUser(User user);

    public abstract int updateUser(Integer userId , User user);

    public abstract Map<Integer , User> list();
}
