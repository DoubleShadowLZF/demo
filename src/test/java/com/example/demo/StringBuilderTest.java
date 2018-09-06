package com.example.demo;

import com.example.demo.redis.component.User;
import com.example.demo.redis.service.BaseUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/9/4
 */
public class StringBuilderTest {

    /*
        行数  设备类型    错误消息    正确消息
              设备型号
   */

    @Test
    public void test(){
        List<String> em = Arrays.asList("发卡终端1","发卡终端2","发卡终端3");
        List<String> tm = Arrays.asList("发卡终端10","发卡终端20","发卡终端30");
        List<String> tempCol = Arrays.asList("设备类型","设备型号","所属会员","设备序列号","设备地址");
//        Map<Integer,List>
    }

    /**
     * @author Double
     * @Description 错误信心不要加符号
     * @param
     * @return java.lang.String
     * @Data 2018/9/4
     */
    public String paresErrorMessage(Map<Integer,List<String>> errorsMap, List<String> message){
        if(errorsMap.size() != message.size()){
            return "入参的Map和List长度应该一致";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < errorsMap.size(); i++){
            List<String> strs = errorsMap.get(i);
            if(!StringUtils.isEmpty(strs)){
                sb.append("第").append(i+1).append("行中，");
                for (String str : strs) {
                    sb.append("错误信息：").append(str).append(",应该为").append(message.get(i)).append(";");
                }
                sb.replace(sb.length()-1,sb.length(),"<br/>");
            }
        }
        return sb.toString();
    }

    public String paresErrorMessage(Map<Integer,String> cosMessage , Map<Integer,List<String>> errors){
        StringBuilder sb = new StringBuilder();
        Map<Integer,List> map =new HashMap<>();

        return sb.toString();
    }
}


