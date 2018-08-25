package com.example.demo.servlet;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description 自定義 servlet
 * @since 2018/8/11
 */
public class MyServlet extends HttpServlet {
    Logger log = LoggerFactory.getLogger(getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("myServlet...");
        resp.getWriter().write("MyServlet....");
    }
}
