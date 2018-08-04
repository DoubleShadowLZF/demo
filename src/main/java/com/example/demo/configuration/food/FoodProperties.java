package com.example.demo.configuration.food;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description Food 配置属性
 * @since 2018/8/4
 */
@ConfigurationProperties(prefix = "configuration.foodProperties")
@ToString
public class FoodProperties {
    private String name ;
    private Calendar date ;
    private String productor;
    private Double price ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
