package com.example.demo.configuration.food;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description Food 配置属性
 * @since 2018/8/4
 */
@Component
@ConfigurationProperties(prefix = "configuration.food")
@ToString
public class FoodProperties {
    private String name ;
    private Date date ;
    private String productor;
    private Double price ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        System.out.println("date:\n"+date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.date = dateFormat.parse(date);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
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
