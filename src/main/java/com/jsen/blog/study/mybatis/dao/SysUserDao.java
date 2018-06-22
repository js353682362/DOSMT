package com.jsen.blog.study.mybatis.dao;

import com.jsen.blog.study.mybatis.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @（#）:SysUserDao.java
 * @description:
 * @author: jiaosen 2018/6/19
 * @version: Version 1.0
 */
@Mapper
public interface SysUserDao {
    SysUser findById(@Param("id") Long id);
}
