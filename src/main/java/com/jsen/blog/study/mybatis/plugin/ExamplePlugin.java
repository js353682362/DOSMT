package com.jsen.blog.study.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * @（#）:ExamplePlugin.java
 * @description:
 * @author: jiaosen 2018/6/21
 * @version: Version 1.0
 */
@Intercepts(@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class}
))
public class ExamplePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("intercept方法  =         === = = =  == ");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        System.out.println("plugin 方法 - = = - = =- = = -");
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("properties");
    }
}
