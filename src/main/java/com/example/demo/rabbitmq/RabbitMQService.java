package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    /**
     * @Description 监听器
     * @param [message 监听到的消息会被json解析器解析成String对象，因此发送的时候，推荐使用RabbitMessageTemplate]
     * @return void
     * @Data 2018/9/2 1:18
     * @author Double
     */
    @RabbitListener(queues = "example.story")
    public void receive(String message){
        System.out.println(">>>"+message);
    }
    @RabbitListener(queues = "example.story")
    public void receive01(Message message){
        System.out.println(">>>"+message.getBody());
        System.out.println(">>>"+message.getMessageProperties());
    }
}
