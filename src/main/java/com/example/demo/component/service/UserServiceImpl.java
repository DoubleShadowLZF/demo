package com.example.demo.component.service;

import com.example.demo.redis.component.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * redis 获取数据服务实现类
 */
@Slf4j
@Service
public class UserServiceImpl extends UserService {
    @Override
    @Cacheable(value="user",key="'user_'+#username")
    public  User getUser() {
        log.debug("redis 获取数据...");
        return new User("Double",23);
    }
}
