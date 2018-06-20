package com.jsen.blog.study.jwt;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @（#）:JwtHelper.java
 * @description:
 * @author: jiaosen 2018/6/15
 * @version: Version 1.0
 */
public class JwtHelper {
    private final static String base64Secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";

    private final static int expireSecond = 1728000;

    public static void main(String[] args) throws UnsupportedEncodingException {

        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC512("cando2015");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
        JWTCreator.Builder jwtBuilder = JWT.create().withIssuer("19");
        String token = jwtBuilder.sign(algorithm);
        System.out.println(token);
    }
}
