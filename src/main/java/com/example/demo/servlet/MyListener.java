package com.example.demo.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description 自定義監聽器
 * @since 2018/8/11
 */
@Slf4j
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("MyListener initialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("MyListener destroy...");
    }
}
