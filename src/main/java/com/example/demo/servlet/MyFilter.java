package com.example.demo.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description 自定義過濾器
 * @since 2018/8/11
 */
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("myFilter...");
        filterChain.doFilter(servletRequest , servletResponse);
    }

    @Override
    public void destroy() {

    }
}
