package com.jsen.blog.study.springsecurity.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsen.blog.study.jwt.JwtHelper;
import com.jsen.blog.study.springsecurity.UserInfo;
import com.jsen.blog.study.springsecurity.bean.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @（#）:JWTLoginFilter.java
 * @description: 验证用户名密码正确后，生成一个token，并将token返回给客户端
 *               该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 *               attemptAuthentication : 接收并解析用户凭证。 successfulAuthentication :
 *               用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * @author: jiaosen 2018/6/15
 * @version: Version 1.0
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    public AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
    }
}
