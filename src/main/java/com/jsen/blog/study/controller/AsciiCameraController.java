package com.jsen.blog.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @（#）:AsciiCamerController.java
 * @description:
 * @author: jiaosen 2017/12/26
 * @version: Version 1.0
 */
@Controller
public class AsciiCameraController {

    @RequestMapping(value = "/asciiCamera", method = RequestMethod.GET)
    public String asciiCamera() {
        return "ascii";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String get(){
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login-error",method = RequestMethod.GET)
    public String fail(){
        return "login-error";
    }


}
