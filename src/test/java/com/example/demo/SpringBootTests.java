package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
