package com.jsen.blog.study.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @（#）:MyDefineDataSourceFactory.java
 * @description:
 * @author: jiaosen 2018/6/19
 * @version: Version 1.0
 */
public class MyDefineDataSourceFactory extends UnpooledDataSourceFactory {
    public MyDefineDataSourceFactory() {
        this.dataSource = new DruidDataSource();
    }
}
