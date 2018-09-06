package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTests {
    /**
     * 获取 SpringBoot 版本号
     */
    @Test
    public void version(){
        Package pkg = SpringBootVersion.class.getPackage();
        System.out.println("当前SpringBoot版本:"+pkg.getImplementationVersion());
    }

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = DemoApplication.class)
    public static class RabbitMQTest {
        @Autowired
        private RabbitTemplate rabbitTemplate;


        /**
         * @Description 测试接收数据
         * @param []
         * @return void
         * @Data 2018/9/2 0:13
         * @author Double
         */
        @Test
        public void receive(){
            Object o = rabbitTemplate.receive("example.story");
            System.out.println(o.getClass());
            System.out.println(o);
        }


        /**
         * @Description redirect 单播模式
         * @param []
         * @return void
         * @Data 2018/9/1 23:25
         * @author Double
         */
        @Test
        public void unicast(){
            Map<String,Object> body = new HashMap<>();
            body.put("name","Double");
            body.put("note","测试");
            body.put("age",24);
            MessageProperties properties = new MessageProperties();

            Message message = new Message(body.toString().getBytes(),properties);
            rabbitTemplate.send("exchange.direct","example.story",message);
        }


        @Autowired
        private RabbitMessagingTemplate rabbitMessagingTemplate;
        @Test
        public void broadcast(){
            rabbitMessagingTemplate.convertAndSend("exchange.fanout","example.news","Hello World");
        }

        @Autowired
        AmqpAdmin amqpAdmin;
        @Test
        public void admin(){
            amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
            amqpAdmin.declareQueue(new Queue("adqpadmin.queue",true));
            amqpAdmin.declareBinding(new Binding("adqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","routingkey",null));
        }

    }
}
