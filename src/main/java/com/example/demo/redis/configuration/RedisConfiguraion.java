package com.example.demo.redis.configuration;


import com.example.demo.redis.component.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description redis 配置类
 * @EnableRedisHttpSession :
 *      设置session失效时间,使用Redis Session 之后,原Boot的server.session.timeout属性不再失效
 *
 * @Data 2018/8/22 9:13
 * @author Double
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class RedisConfiguraion {
    @Bean
    public KeyGenerator simpleKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(o.getClass().getSimpleName());
            stringBuilder.append(".");
            stringBuilder.append(method.getName());
            stringBuilder.append("[");
            for (Object obj : objects) {
                stringBuilder.append(obj.toString());
            }
            stringBuilder.append("]");

            return stringBuilder.toString();
        };
    }

    /**
     * @author Double
     * @Description redis 模板
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
     * @Data 2018/8/19 3:35
     */
    @Bean
    @ConditionalOnMissingBean(name="redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory rf){
        RedisTemplate<String,Object> template = new RedisTemplate();
        //使用fastjson序列化
        FastJsonRedisSerializer<Object> fjrs = new FastJsonRedisSerializer<Object>(Object.class);
        //value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(fjrs);
        template.setHashValueSerializer(fjrs);
        //key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        //开启事务支持
        template.setEnableTransactionSupport(true);

        template.setConnectionFactory(rf);
System.out.printf(">>My redisTemplate is:{%s}\n", template);

        return template;
    }

    /*@Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        //定义key序列化方式
        //RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
        //定义value的序列化方式
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // template.setKeySerializer(redisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }*/


    /**
     * @author Double
     * @Description  缓存管理器
     * @return org.springframework.cache.CacheManager
     * @Data 2018/8/19 3:35
     */
    /*添加此缓存管理器后出现"轉換异常"*/
    /*@Bean
    public CacheManager cacheManager(RedisConnectionFactory rf, RedisTemplate redisTemplate){
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
                                                            .fromConnectionFactory(rf);
        RedisCacheManager.RedisCacheManagerBuilder rcmb = builder.disableCreateOnMissingCache();
        RedisCacheManager manager = builder.build();

        return manager;
    }*/
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                this.getRedisCacheConfigurationWithTtl(600), // 默认策略，未配置的 key 会使用这个
                this.getRedisCacheConfigurationMap() // 指定 key 策略
        );
    }

    public RedisCacheConfiguration getRedisCacheConfigurationWithTtl(int seconds){
        Jackson2JsonRedisSerializer<String> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<String>(String.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer))
                .entryTtl(Duration.ofSeconds(seconds));
        return redisCacheConfiguration;
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("UserInfoList", this.getRedisCacheConfigurationWithTtl(30));
        redisCacheConfigurationMap.put("UserInfoListAnother", this.getRedisCacheConfigurationWithTtl(18000));
        return redisCacheConfigurationMap;
    }

    /**
     * @author Double
     * @Description  对hash 类型的操作
     * @return org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.String,java.lang.Object>
     * @Data 2018/8/19 22:54
     */
    @Bean
    public HashOperations<String,String,Object> hashOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForHash();
    }

    /**
     * @Description 对字符串类型的操作
     * @return org.springframework.data.redis.core.ValueOperations<java.lang.String,java.lang.Object>
     * @Data 2018/8/19 22:57
     * @author Double
     */
    @Bean
    public ValueOperations<String , Object> valueOperations(RedisTemplate redisTemplate){
System.out.printf(">>this is ths valueTemplate's valueTemplate:{%s}\n",redisTemplate);
        return redisTemplate.opsForValue();
    }

    /**
     * @Description 对链表类型的操作
     * @return org.springframework.data.redis.core.ListOperations<java.lang.String,java.lang.Object>
     * @Data 2018/8/19 23:01
     * @author Double
     */
    @Bean
    public ListOperations<String,Object> listOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForList();
    }

    /**
     * @Description 对无序集合类型的操作
     * @param [redisTemplate]
     * @return org.springframework.data.redis.core.SetOperations<java.lang.String,java.lang.Object>
     * @Data 2018/8/19 23:03
     * @author Double
     */
    @Bean
    public SetOperations<String,Object> setOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForSet();
    }

    /**
     * @Description 对有序集合类型的操作
     * @param [redisTemplate]
     * @return org.springframework.data.redis.core.ZSetOperations<java.lang.String,java.lang.Object>
     * @Data 2018/8/19 23:04
     * @author Double
     */
    @Bean
    public ZSetOperations<String,Object> zSetOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForZSet();
    }
}
