package com.example.demo.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Double
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     * @Description 配置Druid的监控,配置一个管理后台的Servlet
     * @param []
     * @return org.springframework.boot.web.servlet.ServletRegistrationBean
     * @Data 2018/8/27 23:11
     * @author Double
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();

        initParams.put("loginUserName","admin");
        initParams.put("loginPassword","123");
        //允许所有访问
        initParams.put("allow","");
        initParams.put("deny","");

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * @Description 配置一个web监控filter
     * @param []
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     * @Data 2018/8/27 23:11
     * @author Double
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean ;
    }

}
