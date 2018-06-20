package com.jsen.blog.study.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

/**
 * @（#）:MybatisBeanConfig.java
 * @description:
 * @author: jiaosen 2018/6/20
 * @version: Version 1.0
 */
public class MybatisBeanConfig {
    public static void main(String[] args) {
        DataSource dataSource = new DruidDataSource();
        //事务管理
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.setLazyLoadingEnabled(true);
    }
}