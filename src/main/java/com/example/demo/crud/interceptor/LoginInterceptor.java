package com.example.demo.crud.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/10
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userName = (String)request.getSession().getAttribute("userName");
        log.debug("LoginInterceptor..."+request.getRequestURI());
        if(!StringUtils.isEmpty(userName)){
            return true ;
        }else{
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false ;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
