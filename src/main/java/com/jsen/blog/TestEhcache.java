package com.jsen.blog;

import com.jsen.blog.study.test.Person;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

/**
 * @（#）:TestEhcache.java
 * @description:
 * @author: jiaosen 2018/3/26
 * @version: Version 1.0
 */
public class TestEhcache {
    public static void main(String[] args) {

        TestEhcache testEhcache = new TestEhcache();
        URL url = testEhcache.getClass().getClassLoader().getResource("ehcache.xml");

        //创建缓存管理
        CacheManager cacheManager = CacheManager.create(url.getPath());

        //获取缓存对象
        Cache cache = cacheManager.getCache("HelloWorldCache");

        //创建元素
        Element element = new Element("key", "value");

        //元素添加到缓存
        cache.put(element);

        //获取缓存
        Element el = cache.get("key");
        System.out.println(el);
        System.out.println(el.getObjectValue());

        //删除元素
        cache.remove("key");

        Person person = new Person("abc","11");
        Element element1 = new Element("abc",person);
        cache.put(element1);
        Element el2 = cache.get("abc");
        Person per = (Person) el.getObjectValue();
        System.out.println(per);

        //缓存刷新
        cache.flush();
        //关闭缓存管理
        cacheManager.shutdown();
    }
}
