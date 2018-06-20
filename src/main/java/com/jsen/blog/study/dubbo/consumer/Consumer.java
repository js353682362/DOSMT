package com.jsen.blog.study.dubbo.consumer;

        import com.jsen.blog.study.dubbo.provider.service.DemoService;
        import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @（#）:Consumer.java
 * @description:
 * @author: jiaosen 2018/6/14
 * @version: Version 1.0
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-demo-consumer.xml");
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService");
        String hello = demoService.sayHello(" world");
        System.out.println(hello);
    }
}