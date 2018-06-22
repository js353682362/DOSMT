package com.jsen.blog.study.listener;

import com.jsen.blog.study.mybatis.dao.SysUserDao;
import com.jsen.blog.study.mybatis.entity.SysUser;
import com.jsen.blog.study.redis.DistributedLockHandler;
import com.jsen.blog.study.redis.Lock;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @（#）:RedisTestListener.java
 * @description:
 * @author: jiaosen 2018/6/21
 * @version: Version 1.0
 */
public class RedisTestListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        System.out.println("listener启动---------");
//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        StringRedisTemplate redis = contextRefreshedEvent.getApplicationContext().getBean(StringRedisTemplate.class);
//        redis.opsForValue().set("abc", "测试");
//        redis.opsForList().leftPushAll("qq", list);
//        redis.opsForList().range("qwe", 0, -1).forEach(System.out::println);
        Lock lock = new Lock("sss","1111");
        DistributedLockHandler distributedLockHandler = contextRefreshedEvent.getApplicationContext().getBean(DistributedLockHandler.class);
        new Thread(() -> {
            if(distributedLockHandler.tryLock(lock)){
                System.out.println("第一次     成功获得锁");
            }
        }).start();
        new Thread(() -> {
            if(distributedLockHandler.tryLock(lock)){
                System.out.println("第二次     成功获得锁");
                distributedLockHandler.releaseLock(lock);
            }
        }).start();
        new Thread(() -> {
            if(distributedLockHandler.tryLock(lock)){
                System.out.println("第三次     成功获得锁");
                distributedLockHandler.releaseLock(lock);
            }
        }).start();

        SysUserDao sysUserDao = contextRefreshedEvent.getApplicationContext().getBean(SysUserDao.class);
        SysUser sysUser = sysUserDao.findById(1L);
        System.out.println(sysUser);
    }
}
