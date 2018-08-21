package com.example.demo.autoconfiguration.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/5
 */
public class FoodInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String strDate = request.getParameter("date");
        if(strDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date foodDate = simpleDateFormat.parse(strDate);
            Date date = new Date();
            System.out.println("now:\n" + date);
            return foodDate.before(date);
        }
        return true;
    }

    /**
     * @author Double
     * @Description 处理过期的食物
     * @Data 2018/8/5
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
