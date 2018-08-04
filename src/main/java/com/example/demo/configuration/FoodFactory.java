package com.example.demo.configuration;

import com.example.demo.configuration.food.FoodProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/4
 */
public class FoodFactory {

    private final static FoodFactory foodFactory = new FoodFactory();

    @Autowired
    private static FoodProperties foodPropertiesOfFactory = null ;

    public static FoodFactory getInstance(FoodProperties foodProperties) {
        foodPropertiesOfFactory = foodProperties;
         return foodFactory;
    }

    public static Object getFood(String foodName){
        if("MilkTea".equals(foodName)) {
            return "MilkTea";
        }else{
            return "none food" ;
        }
    }
}
