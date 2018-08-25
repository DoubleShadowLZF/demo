package com.example.demo.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description 自定義監聽器
 * @since 2018/8/11
 */
public class MyListener implements ServletContextListener {

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("MyListener initialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("MyListener destroy...");
    }
}
