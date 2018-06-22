package com.jsen.blog.study.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @（#）:DistributedLockHandler.java
 * @description: 分布式锁
 * @author: jiaosen 2018/6/22
 * @version: Version 1.0
 */
@Slf4j
@Component
public class DistributedLockHandler {

    /**
     * 使用方式
         @Autowired
         DistributedLockHandler distributedLockHandler;

         Lock lock=new Lock("lockk","sssssssss);

         if(distributedLockHandler.tryLock(lock){
         doSomething();
         distributedLockHandler.releaseLock();
         }
     */


    /**
     * 单个业务持有锁的时间30s，防止死锁
     */
    private final static long LOCK_EXPIRE = 30 * 1000L;

    /**
     * 默认30s尝试一次
     */
    private static final long LOCK_TRY_INTERVAL = 30L;

    /**
     * 默认尝试20s
     */
    private static final long LOCK_TRY_TIMEOUT = 20 * 1000L;

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 尝试获取全局锁
     *
     * @param lock
     * @return
     */
    public boolean tryLock(Lock lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock
     * @param timeout
     * @return
     */
    public boolean tryLock(Lock lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock
     * @param timeout
     * @param tryInterval
     * @return
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval) {
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock
     * @param timeout
     * @param tryInterval
     * @param lockExpireTime
     * @return
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        return getLock(lock, timeout, tryInterval, lockExpireTime);
    }

    /**
     * 操作redis获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取的超时时间
     * @param tryInterval    多少ms尝试一次
     * @param lockExpireTime 获取成功后锁的过期时间
     * @return true 获取成功，false获取失败
     */
    public boolean getLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        try {
            if (StringUtils.isBlank(lock.getName()) || StringUtils.isEmpty(lock.getValue())) {
                return false;
            }

            long startTime = System.currentTimeMillis();
            do {
                if (!redisTemplate.hasKey(lock.getName())) {
                    //操作字符串
                    //redisTemplate.opsForValue();//操作字符串
                    //redisTemplate.opsForHash();//操作hash
                    //redisTemplate.opsForList();//操作list
                    //redisTemplate.opsForSet();//操作set
                    //redisTemplate.opsForZSet();//操作有序set
                    ValueOperations<String, String> ops = redisTemplate.opsForValue();
                    ops.set(lock.getName(), lock.getValue(), lockExpireTime, TimeUnit.MILLISECONDS);
                    return true;
                } else {
                    log.debug("lock is exist!!!");
                }
                //尝试超过了设定值之后直接跳出循环
                if (System.currentTimeMillis() - startTime > timeout) {
                    return false;
                }
                Thread.sleep(tryInterval);
            } while (redisTemplate.hasKey(lock.getName()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public void releaseLock(Lock lock) {
        if (StringUtils.isNotBlank(lock.getName())) {
            redisTemplate.delete(lock.getName());
        }
    }
}
