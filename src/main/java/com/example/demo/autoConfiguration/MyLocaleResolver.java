package com.example.demo.autoConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/8
 */
public class MyLocaleResolver implements LocaleResolver {

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = Locale.getDefault();
        String lang = request.getParameter("l");
        if(lang != null && lang.length()>0){
            String[] s = lang.split("_");
            locale = new Locale(s[0], s[1]);
        }
        log.debug("resolveLocale...");
        return  locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
