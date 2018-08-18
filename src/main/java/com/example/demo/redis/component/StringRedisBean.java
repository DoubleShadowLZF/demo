package com.example.demo.redis.component;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Getter @ToString
public class StringRedisBean {
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public StringRedisBean(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

}
