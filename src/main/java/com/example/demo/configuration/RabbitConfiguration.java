package com.example.demo.configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    /**
     * @Description
     * 接收类型：
     * content_type:
     *      application/json
     * @param []
     * @return org.springframework.amqp.support.converter.MessageConverter
     * @Data 2018/9/2 0:42
     * @author Double
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter() ;
    }
}
