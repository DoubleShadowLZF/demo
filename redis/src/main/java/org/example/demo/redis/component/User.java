package org.example.demo.redis.component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;

/**
 * @Description User 测试类
 * @Data 2018/8/21 9:33
 * @author Double
 */
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@JsonSerialize
public class User implements Serializable {
    private Integer id;
    private String name ;
    private Integer age ;

}
