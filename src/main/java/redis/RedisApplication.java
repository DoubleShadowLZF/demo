package redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description 
 *   Redis 模块
 *
 * @Data 2018/8/22 9:16
 * @author Double
 */
@SpringBootApplication
@EnableCaching
public class RedisApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(RedisApplication.class, args);
    }
}
