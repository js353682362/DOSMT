package com.jsen.blog.study.threadPool;

import java.util.List;

/**
 * @（#）:ThreadPool.java
 * @description: 线程接口
 * @author: jiaosen 2018/3/27
 * @version: Version 1.0
 */
public interface IThreadPool {
    /**
     * 加入任务
     * @param task
     */
    void execute(Runnable task);

    /**
     * 加入任务
     * @param tasks
     */
    void execute(Runnable[] tasks);

    /**
     * 加入任务
     * @param taskList
     */
    void execute(List<Runnable> taskList);

    /**
     * 销毁线程
     */
    void destroy();
}
