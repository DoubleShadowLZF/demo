package com.example.demo;

import com.example.demo.configuration.FoodFactory;
import com.example.demo.properties.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * @author Double
     * @Description Properties 文件测试
     *  1.properties文件支持 .properties 或 .yml 结尾的文件,
     *  优先级: ①-file./config     ②-file:./   ③classoath:./config    ④classpath:./
     *  2. 通过 @ConfigurationProperties(prefix="person") 指定配置送伞绑定的属性值,
     *  @PropertySource(value = {"classpath:person.properties"}) 可以指定配置文件的路径,指定的配置文件的配置信息会覆盖application.yml 的配置信息
     * @Data 2018/8/5
     */
    @Autowired
    Person person;
    @Test
    public void testProperties(){
        System.out.println("person:"+person);
    }

    /**
     * @author Double
     * @Description 测试 Configuration ，
     *  FoodConfiguration 通过 @EnableConfigurationProperties(FoodProperties.class) 自动注入到foodProperties属性，
     *  由于 类上标记了 @Configuration ,通过 @Bean 注解将方法 getFoodFactory()的返回值 FoodFactory 类型的对象注入到Bean容器中，
     *  可以使用 @Autowird 取出Bean容器中的FoodFactory 对象。
     * @Data 2018/8/5
     */
    @Autowired
    private FoodFactory foodFactory;
    @Test
    public void testConfiguration(){
        System.out.println(">>>get milk tea :\n" + foodFactory.getFood("MilkTea"));
    }


    /**
     * @author Double
     * @Description 测试 SLF4J
     * SpringBoot 默认使用的是  SLF4j + Logback ,
     *  1.默认加载的配置文件: classpath:logback.xml 或 logback-spring.xml ,
     *  使用 *-spring.xml 在配置文件中可以使用高级profile功能(springProfile),
     *  可以通过指定配置文件 application.yml 的loging.config 指定日志的配置文件;
     *  2. logback-spring.xml 配置文件的配置
     * @Data 2018/8/5
     */
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void testLog(){
        logger.trace("trace...");
        logger.debug("debug...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");
    }

}
