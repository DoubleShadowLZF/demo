package com.example.demo.servlet;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description WebServerFactoryCustomizer 代替了 EmbeddedServletContainerCustomizer ，
 *              汎型支持 AbstractReactiveWebServerFactory ：
 *                  ①NettyReactiveWebServerFactory ，②UndertowReactiveWebServerFactory ,
 *                  ③JettyReactiveWebServerFactory ,④TomcatReactiveWebServerFactory ，
 *
 * @since 2018/8/11
 */
@Component
public class MyTomcatWebServerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Override
    public void customize(TomcatServletWebServerFactory server) {
        int port = server.getPort();
        server.setPort(9000);
        //直接在 TomcatServletWebServerFactory 实例设置参数
        server.setUriEncoding(StandardCharsets.UTF_8);
    }
}
