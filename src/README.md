#SpringBoot Demo
该项目是Double用来记录SpringBoot学习过程中的测试代码，以及记录过程中的一些坑。
##配置文件
###@Value
##日志
##日志配置
###切换日志
##Thymeleaf
##CRUD
###Restful风格请求
###拦截器
##嵌入式Tomcat
###Servlet
###filter
###listener
##切面
##Redis
###Redis配置
###Redis注解
####Redis注解配置有效时间
```java
@Configuration
public class RedisConfiguraion {
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
}
```
配置完配置文件后，直接在需要使用的接口上使用注解即可。
```java
    //UserInfoList对应的是30s,如果指定的value在getRedisCacheConfigurationMap没有指定的话,则使用默认600s.
    @Cacheable(value = "UserInfoList", keyGenerator = "simpleKeyGenerator") 
    public Map<Integer, User> queryExpireList() {
        log.debug(">>>queryList...");
        return users;
    }
```
###Redis集群
##任务
###异步任务
###定时任务
###邮件任务
##安全
##文件上传
