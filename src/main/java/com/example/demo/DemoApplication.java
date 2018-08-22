package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        //ListableBeanFactory 的 getBeansOfType 可以获取父类下所有加入了 bean容器的所有实例对象
        Map<String, ConfigurableServletWebServerFactory> beans = context.getBeansOfType(ConfigurableServletWebServerFactory.class);
        System.out.println(">>>ConfigurableServletWebServerFactory:"+beans);
    }
}
