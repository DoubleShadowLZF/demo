package com.example.demo.configuration;

import com.example.demo.configuration.food.FoodProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/5
 */
public class FoodFactory {

    private static final FoodFactory FOOD_FACTORY = new FoodFactory();

    private Map<String , FoodProperties> food = new HashMap<>();

    private FoodFactory(){}

    public static FoodFactory getInstance() {
        return FOOD_FACTORY;
    }

    public Object getFood(String foodName){
        return food.get(foodName) ;
    }

    public void putFood(String foodName , FoodProperties foodProperties) {
        food.put(foodName , foodProperties);
    }
}
