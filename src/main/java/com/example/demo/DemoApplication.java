package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

/**
 * @author Double
 */
@SpringBootApplication
@Slf4j
@EnableScheduling
@EnableAsync
@EnableCaching
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        //ListableBeanFactory 的 getBeansOfType 可以获取父类下所有加入了 bean容器的所有实例对象
        Map<String, ConfigurableServletWebServerFactory> beans = context.getBeansOfType(ConfigurableServletWebServerFactory.class);
System.out.println(">>>ConfigurableServletWebServerFactory:"+beans);
    }
}
