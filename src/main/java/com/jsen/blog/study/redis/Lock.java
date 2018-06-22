package com.jsen.blog.study.redis;

import lombok.Data;

/**
 * @（#）:Lock.java
 * @description:
 * @author: jiaosen 2018/6/22
 * @version: Version 1.0
 */
@Data
public class Lock {
    private String name;

    private String value;

    public Lock(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Lock() {
    }
}
