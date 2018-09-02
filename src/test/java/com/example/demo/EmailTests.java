package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailTests {

    @Autowired
    private JavaMailSenderImpl sender ; //= new JavaMailSenderImpl();

    private final String EMAIL_FROM = "li1009083442@163.com";
    private final String EMAIL_TO = "1009083442@qq.com";


    @Test
    public void email(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("紧急通知");
        message.setText("我的名字叫李卓锋，现在在广州天河科学城路口，码代码啦~~");

        message.setFrom(EMAIL_FROM);
        message.setTo(EMAIL_TO);
        sender.send(message);
    }

    @Test
    public void emailWithFile() throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.addAttachment("559dec8689bca_1024.jpg",new File("C:\\Users\\Double\\Pictures\\cartoon\\559dec8689bca_1024.jpg"));
        helper.setSubject("福利");
        helper.setText("银桑的图片~");
        helper.setText("<p style='color:red'>小时候</p>",true);

        helper.setFrom(EMAIL_FROM);
        helper.setTo(EMAIL_TO);
        sender.send(mimeMessage);
        System.out.println("发送包含文件的邮件完成。。。");
    }
}
