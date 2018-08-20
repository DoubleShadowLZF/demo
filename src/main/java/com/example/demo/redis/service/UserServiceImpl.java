package com.example.demo.redis.service;

import com.example.demo.redis.component.User;
import com.example.demo.redis.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * redis 获取数据服务实现类
 */
@Slf4j
@Service
public class UserServiceImpl extends UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Cacheable(value="users",key="'users_'+#username")
    public User queryUser(Integer userId , User user) {

        return userDao.queryUser(userId , user);
    }

    @Override
    @Cacheable(value="users",key="'users_'+#username")
    public int insertUser(User user) {
//        redisTemplate.opsForValue().set("users",);
        return userDao.insertUser(user);
    }

    @Override
    @CacheEvict(value="users",key="'users_'+#username")
    public int updateUser(Integer userId , User user) {
        return userDao.updateUser(userId , user);
    }

    @Override
    public Map<Integer , User> list() {
        return userDao.queryList();
    }
}
