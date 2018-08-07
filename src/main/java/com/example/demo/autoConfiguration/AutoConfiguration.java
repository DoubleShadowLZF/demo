package com.example.demo.autoConfiguration;

import com.example.demo.autoConfiguration.interceptor.FoodInterceptor;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
/**
 * @author 李卓锋
 * @version 1.0
 * @Description 测试 SpringMvc 自动配置
 * @since 2018/8/5
 */
@Configuration
public class AutoConfiguration extends WebMvcConfigurationSupport {

    /**
     * @author Double
     * @Description 前端访问 http://localhost:8082/viewController/viewController 会被映射到 "viewController"
     * @return void
     * @Data 2018/8/5
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("viewController").setViewName("/viewController");
        registry.addViewController("login").setViewName("/viewController");
    }

    /**
     * @author Double
     * @Description 添加拦截器
     * @Data 2018/8/5
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FoodInterceptor());
    }
}
