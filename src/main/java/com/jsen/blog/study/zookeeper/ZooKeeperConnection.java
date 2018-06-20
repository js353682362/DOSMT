package com.jsen.blog.study.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @（#）:ZooKeeperConnection.java
 * @description:
 * @author: jiaosen 2018/6/14
 * @version: Version 1.0
 */
public class ZooKeeperConnection {

    private ZooKeeper zoo;

    private final CountDownLatch connectedSignal = new CountDownLatch(1);

    public ZooKeeper connect(String host) throws IOException, InterruptedException {
        /**
         * connectionString - ZooKeeper集合主机。
         *
         * sessionTimeout - 会话超时（以毫秒为单位）。
         *
         * watcher - 实现“监视器”界面的对象。ZooKeeper集合通过监视器对象返回连接状态。
         */
        zoo = new ZooKeeper(host, 5000, watchedEvent -> {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                connectedSignal.countDown();
            }
        });
        connectedSignal.await();
        return zoo;
    }

    public void close() throws InterruptedException {
        zoo.close();
    }
}
