package com.jsen.blog.study.threadPool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @（#）:ThreadPoolImp.java
 * @description:
 * @author: jiaosen 2018/3/28
 * @version: Version 1.0
 */
public class ThreadPoolImpl implements IThreadPool {

    // 默认开启线程个数
    static int WORKER_NUMBER = 5;

    // 完成任务线程数 可见性
    static volatile int sumCount = 0;

    // 任务队列list非线程安全，可以优化为BlockQueue
    static List<Runnable> taskQueue = new LinkedList<>();

    // 线程工作组
    static AtomicLong threadNum = new AtomicLong();
    WorkerThread[] workerThreads;

    // 原子性

    static ThreadPoolImpl threadPool;

    private ThreadPoolImpl() {
        this(WORKER_NUMBER);
    }

    private ThreadPoolImpl(int workNum) {
        ThreadPoolImpl.WORKER_NUMBER = workNum;
        // 开辟工作线程空间
        workerThreads = new WorkerThread[ThreadPoolImpl.WORKER_NUMBER];

        for (int i = 0; i < WORKER_NUMBER; i++) {
            workerThreads[i] = new WorkerThread();
            Thread thread = new Thread(workerThreads[i],
                    "ThreadPool-worker" + threadNum.incrementAndGet());
            System.out.println("初始化线程数" + (i + 1) + "-------当前线程名称" + thread.getName());
            thread.start();
        }
    }

    @Override
    public String toString() {
        return "工作线程数量为" + WORKER_NUMBER + "已完成的任务数" + sumCount + "等待任务数量" + taskQueue.size();
    }

    /**
     * 获取线程池
     *
     * @return
     */
    public static IThreadPool getThreadPool() {
        return getThreadPool(WORKER_NUMBER);
    }

    /***
     * 获取线程池
     *
     * @param workerNum
     * @return
     */
    public static IThreadPool getThreadPool(int workerNum) {
        // 容错性，如果小于0就默认线程数
        if (workerNum < 0) {
            workerNum = WORKER_NUMBER;
        }

        if (threadPool == null) {
            threadPool = new ThreadPoolImpl(workerNum);
        }
        return threadPool;
    }

    /**
     * 加入任务
     *
     * @param task
     */
    @Override
    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    /**
     * 加入任务
     *
     * @param tasks
     */
    @Override
    public void execute(Runnable[] tasks) {
        synchronized (taskQueue) {
            for (Runnable task : tasks) {
                taskQueue.add(task);
            }
            taskQueue.notifyAll();
        }
    }

    /**
     * 加入任务
     *
     * @param taskList
     */
    @Override
    public void execute(List<Runnable> taskList) {
        synchronized (taskQueue) {
            taskList.forEach(task -> taskQueue.add(task));
            taskQueue.notifyAll();
        }
    }

    /**
     * 销毁线程
     */
    @Override
    public void destroy() {
        // 循环是否还存在任务,如果存在等待20ms处理时间
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < WORKER_NUMBER; i++) {
            workerThreads[i].setWorkerFlag();
            workerThreads[i] = null;
        }
        threadPool = null;
        taskQueue.clear();
    }

    class WorkerThread implements Runnable {
        private boolean isRunning = true;

        @Override
        public void run() {
            Runnable runnable = null;
            while (isRunning) {
                // 非线程安全，所以采用同步锁
                synchronized (taskQueue) {
                    while (isRunning && taskQueue.isEmpty()) {
                        try {
                            // 如果任务队列为空，等待20ms 监听任务到达
                            taskQueue.wait(20);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (!taskQueue.isEmpty()) {
                        // 获取第一个任务
                        runnable = taskQueue.remove(0);
                    }
                }
                if (runnable != null) {
                    runnable.run();
                }
                sumCount++;
                runnable = null;
            }
        }

        private void setWorkerFlag() {
            isRunning = false;
        }

    }

}
