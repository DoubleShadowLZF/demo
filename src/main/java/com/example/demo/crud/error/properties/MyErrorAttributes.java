package com.example.demo.crud.error.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/11
 */
@Component
@Slf4j
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        log.debug("MyErrorAttributes...");
        Map<String, Object> errors = super.getErrorAttributes(webRequest, includeStackTrace);
        String smg = (String) webRequest.getAttribute("smg", RequestAttributes.SCOPE_REQUEST);
        errors.put("smg",smg);
        Map ext = (Map) webRequest.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        errors.put("ext",ext);
        Exception e = (Exception) webRequest.getAttribute("exception", RequestAttributes.SCOPE_REQUEST);
        if(e != null) {
            errors.put("exception", e.getMessage());
        }
        return errors;
    }
}
