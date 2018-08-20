package com.example.demo.redis.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//设置session失效时间,使用Redis Session 之后,原Boot的server.session.timeout属性不再失效
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class RedisConfiguraion {
}
