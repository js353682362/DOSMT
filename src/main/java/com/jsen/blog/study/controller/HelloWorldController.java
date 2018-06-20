package com.jsen.blog.study.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @（#）:HelloworldController.java
 * @description:
 * @author: jiaosen 2018/6/15
 * @version: Version 1.0
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String helloWorld()
    {
        return "spring security hello world";
    }

    @RequestMapping("/whoim")
    public Object whoIm()
    {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
