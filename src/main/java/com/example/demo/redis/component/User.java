package com.example.demo.redis.component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description User 测试类
 * @Data 2018/8/21 9:33
 * @author Double
 */
@Getter @Setter @ToString @NoArgsConstructor
@JsonSerialize

public class User implements Serializable {
    private Integer id;
    private String name ;
    private Integer age ;

    public User(Integer id,String name, Integer age) {
        this.id = id ;
        this.name = name;
        this.age = age;
    }
}
