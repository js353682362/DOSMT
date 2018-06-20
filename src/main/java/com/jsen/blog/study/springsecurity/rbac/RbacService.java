package com.jsen.blog.study.springsecurity.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @（#）:RbacService.java
 * @description:
 * @author: jiaosen 2018/6/15
 * @version: Version 1.0
 */
public interface RbacService {
    /**
     * 返回权限验证的接口
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
