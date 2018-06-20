package com.jsen.blog.study.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @（#）:SelectorTest.java
 * @description:
 * @author: jiaosen 2018/6/20
 * @version: Version 1.0
 */
public class SelectorTest {

    /**
     * new ThreadPoolExecutor(corePoolSize, maximumPoolSize,keepAliveTime,
     * milliseconds,runnableTaskQueue, threadFactory,handler); corePoolSize
     * 线程池基本大小;如果调用了线程池的prestartAllCoreThreads方法，线程池会提前创建并启动所有基本线程 maximumPoolSize
     * 线程池最大大小 keepAliveTime 线程活动保持时间
     * 线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大这个时间，提高线程的利用率 TimeUnit
     * 线程活动保持时间的单位 runnableTaskQueue 用于保证等待执行的任务的阻塞队列
     */
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1000, 1, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(1), new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    System.out.println("reject ...");
                }
            });

    /**
     *
     * 创建 ServerSocketChannel 和业务处理线程池。
     * 绑定监听端口，并配置为非阻塞模式。
     * 创建 Selector，将之前创建的 ServerSocketChannel 注册到 Selector 上，监听 SelectionKey.OP_ACCEPT。
     * 循环执行 Selector.select() 方法，轮询就绪的 Channel。 轮询就绪的 Channel 时，如果是处于 OP_ACCEPT 状态，
     *   说明是新的客户端接入，调用 ServerSocketChannel.accept 接收新的客户端。
     * 设置新接入的 SocketChannel为非阻塞模式，并注册到 Selector 上，监听 OP_READ。
     * 如果轮询的 Channel 状态是 OP_READ，说明有新的就绪数据包需要读取，则构造 ByteBuffer 对象，读取数据。
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 绑定监听端口，并配置为非阻塞模式。
        serverSocketChannel.socket().bind(new InetSocketAddress(9078));
        serverSocketChannel.configureBlocking(false);

        // 创建 Selector，将之前创建的 ServerSocketChannel 注册到 Selector 上，监听
        // SelectionKey.OP_ACCEPT。
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach("accept_channel");

        long start = System.currentTimeMillis();

        // 循环执行 Selector.select() 方法，轮询就绪的 Channel。 轮询就绪的 Channel 时，如果是处于 OP_ACCEPT 状态，
        // 说明是新的客户端接入，调用 ServerSocketChannel.accept 接收新的客户端。
        while (true) {
            int readyNum = selector.select();
            System.out.println(
                    "\nready num: " + readyNum + ", time: " + (System.currentTimeMillis() - start));

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                String tag = (String) next.attachment();
                if (next.isAcceptable()) {

                    // 设置新接入的 SocketChannel为非阻塞模式，并注册到 Selector 上，监听 OP_READ
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    String socketName = "socket_" + atomicInteger.incrementAndGet();
                    socketChannel.register(selector, SelectionKey.OP_READ, socketName);

                    System.out.println(tag + "new SocketChannle:" + socketName);
                } else if (next.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) next.channel();
                    pool.submit(new SocketServerRunnable(socketChannel, tag));
                    next.cancel();
                }
                iterator.remove();
            }
        }
    }
}
