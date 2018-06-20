package com.jsen.blog.study.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @（#）:Provider.java
 * @description:
 * @author: jiaosen 2018/6/14
 * @version: Version 1.0
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-demo-provider.xml");
        context.start();
        System.in.read();
    }
}
