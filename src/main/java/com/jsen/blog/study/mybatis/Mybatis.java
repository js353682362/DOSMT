package com.jsen.blog.study.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.jsen.blog.study.mybatis.entity.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @（#）:Mybatis.java
 * @description:
 * @author: jiaosen 2018/6/19
 * @version: Version 1.0
 */
public class Mybatis {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis/config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<SysUser> result = sqlSession.selectList("getUser");
        System.out.println(Arrays.toString(result.toArray()));


        System.out.println();
        System.out.println();

        System.out.println(Mybatis.class.getResource("").getPath());
        System.out.println(Mybatis.class.getResource("/").getPath());

        ClassLoader classLoader = Mybatis.class.getClassLoader();
        System.out.println(classLoader.getResource(""));
        System.out.println(classLoader.getResource("/"));
    }
}
