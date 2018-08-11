package com.example.demo.crud.controller;

import com.example.demo.crud.error.exception.ParamErrorException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/11
 */
@Controller
public class ErroController {

    @GetMapping("/myError")
    public Object error(String param){
        if(StringUtils.isEmpty(param)){
            throw new ParamErrorException();
        }
        return "/";
    }

}
