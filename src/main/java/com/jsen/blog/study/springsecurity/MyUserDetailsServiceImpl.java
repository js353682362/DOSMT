package com.jsen.blog.study.springsecurity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @（#）:MyUserDetailsService.java
 * @description:
 * @author: jiaosen 2018/6/15
 * @version: Version 1.0
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 这里可以通过username（登陆时输入的用户名）然后到数据库中找到对应的用户信息并构建成我们自己的UserInfo来返回。

        // 这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        if ("admin".equals(username)) {
            // 假设返回的用户信息如下;
            return new UserInfo("admin", "123456", "ROLE_ADMIN", true, true, true, true);

        }

        if("user".equals(username)){
            return new UserInfo("admin", "123456", "ROLE_SSS", true, true, true, true);
        }
        return null;
    }
}
