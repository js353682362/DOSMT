package com.jsen.blog.study.mybatis.entity;

import lombok.Data;

/**
 * @（#）:SysUser.java
 * @description:
 * @author: jiaosen 2018/6/19
 * @version: Version 1.0
 */
@Data
public class SysUser {
    private Long id;

    private String name;

    private String loginName;

    private String password;

    private String email;
}
