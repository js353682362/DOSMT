package com.jsen.blog.study.dubbo.provider.service;

/**
 * @（#）:DemoServiceImpl.java
 * @description:
 * @author: jiaosen 2018/6/14
 * @version: Version 1.0
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
