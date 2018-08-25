package com.example.demo.crud.error.handler;

import com.example.demo.crud.error.exception.ParamErrorException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/11
 */

@ControllerAdvice
public class ParamErrorExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @author Double
     * @Description
     *      @ExceptionHandler ：
     *          異常處理器，其注解的作用是儅出現其定義的異常時進行處理的方法，其可以使用SpringMvc提供的數據綁定，
     *      比如注入HttpServletRequest等，還可以接受一個當前抛出的Throwable 對象。
     *
     * @param
     * @return java.lang.String
     * @Data 2018/8/11
     */
    @ExceptionHandler(ParamErrorException.class)
    public String handleException(Exception e , HttpServletRequest request){
        Map<String , Object> map = new HashMap<>();
        log.debug("PasswordErrorException:"+e);
        map.put("msg","密码是否正确...");
        request.setAttribute("javax.servlet.error.status_code",500);
        request.setAttribute("msg","密码是否正确...");
        request.setAttribute("ext",map);
        request.setAttribute("exception",e);
        return "forward:/error";
    }
}
