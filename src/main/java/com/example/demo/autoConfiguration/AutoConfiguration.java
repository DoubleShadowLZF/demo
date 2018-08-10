package com.example.demo.autoConfiguration;

import com.example.demo.autoConfiguration.interceptor.FoodInterceptor;
import com.example.demo.crud.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.util.UrlPathHelper;

import java.util.List;
import java.util.Locale;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description 测试 SpringMvc 自动配置
 * @since 2018/8/5
 */
@Configuration
public class AutoConfiguration implements WebMvcConfigurer {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        /*设置为 true时 http://localhost:8082/SpringBoot/test.test 也将会被映射到 /test 路径上
         * 注意: 此处使用了定界符".",即URL:"/test.*"都会被"/test"处理。
         */
        configurer.setUseRegisteredSuffixPatternMatch(true)
        //设置斜杠匹配,"/test[/]?" 都会被映射到 "/test" 请求上
                .setUseTrailingSlashMatch(true)
        // 和 setUseRegisteredSuffixPatternMatch 效果相同
                .setUseSuffixPatternMatch(true)
        // 自定义 PathMatcher ,SpringMvc 默认只有一个 AntPathMatcher
                .setPathMatcher(new MyPathMatcher())
                .setUrlPathHelper( new UrlPathHelper());
        log.debug("pathMatcher :"+configurer.getPathMatcher());
        log.debug("urlPathHelper:"+configurer.getUrlPathHelper());
    }

    /**
     * @author Double
     * @Description  配置内容协商选项,确定要返回的数据格式称为内容协商。
     *              请求头的 accept
     *              Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*\/*;q=0.8
     * @param
     * @return void
     * @Data 2018/8/8
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                favorParameter(true).
                parameterName("mediaType").
                ignoreAcceptHeader(true).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON).
                //内容協商默認是text/html，如果json格式，將不能跳轉到template目錄下的錯誤頁面
//                defaultContentType(MediaType.TEXT_HTML)
                defaultContentType(MediaType.TEXT_HTML);
    }

    /**
     * @author Double
     * @Description
     * @param
     * @return void
     * @Data 2018/8/8
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }

    /**
     * @author Double
     * @Description 前端访问 http://localhost:8082/viewController/viewController 会被映射到 "viewController"
     * @return void
     * @Data 2018/8/5
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("viewController").setViewName("/viewController");
        registry.addViewController("login").setViewName("/viewController");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //登陸后，直接轉發到該請求，避免刷新重複提交表單的問題。
        registry.addViewController("/dashboard.html").setViewName("dashboard");
    }

    /**
     * @author Double
     * @Description 添加拦截器
     * @Data 2018/8/5
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FoodInterceptor());
        /* 登陸的攔截請求應該排除登陸本身的請求，不然會一直被攔截，抛出異常；
         * 還有驗證信息的請求，由於攔截器是使用session 中的userName 作爲驗證信息，所以不需要放行“/dashboard.html”請求。
         */

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/login",
                //1.*.* 版本默认不对静态资源进行拦截,2.*.* 会对静态资源进行拦截.
                "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/fonts/*","/**/*.svg",
                //錯誤頁面測試
                "/error");
    }

    /*
     * ContentNegotiatingViewResolver 会将所用Bean容器中的 ViewResolver 对象提取出来,
     * 并执行它的功能。
     */
    @Bean
    public ViewResolver viewResolver(){
        return new MyViewResolver ();
    }

    private static class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }

    /**
     * @author Double
     * @Description 测试 LocaleResolver
     *  自定义 LocaleResolver ,替换SpringBoot 原本的.
     * @param
     * @return org.springframework.web.servlet.LocaleResolver 自定义的 LocaleResolver
     * @Data 2018/8/8
     */
    //TODO 如果 @Bean 没有标注 localeResolver , 则该 LocaleResolver 无法注入。
    @Bean("localeResolver")
    public LocaleResolver  myLocaleResolver(){
        return new MyLocaleResolver();
    }

}
