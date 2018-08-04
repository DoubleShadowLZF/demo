package com.example.demo.configuration;

import com.example.demo.configuration.food.FoodProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description Configuration 配置类测试
 * @since 2018/8/4
 */
@Configuration
//@EnableConfigurationProperties(FoodProperties.class)
//@ConditionalOnProperty(
//        prefix = "configuration.foodProperties.productor",
//        value={"Double"},
//        matchIfMissing = true
//)
public class FoodConfiguration {

//    @Autowired
//    private FoodProperties foodProperties;

//    public FoodConfiguration(FoodProperties foodProperties) {
//        this.foodProperties = foodProperties;
//    }

//    @Bean
//    public Object getFoodFactory(){
////        return FoodFactory.getInstance(foodProperties);
//        return foodProperties;
//    }
}
