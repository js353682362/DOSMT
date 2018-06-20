package com.jsen.blog.study.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @（#）:ZKExists.java
 * @description:
 * @author: jiaosen 2018/6/14
 * @version: Version 1.0
 */
public class ZKExists {

    private static ZooKeeper zooKeeper;

    private static ZooKeeperConnection zooKeeperConnection;

    public static Stat znodeExists(String path) throws KeeperException, InterruptedException {
        /**
         * path- Znode路径
         *
         * watcher - 布尔值，用于指定是否监视指定的znode
         */
        return zooKeeper.exists(path, true);
    }

    public static void main(String[] args) throws InterruptedException, KeeperException {
        zooKeeperConnection = new ZooKeeperConnection();
        String path = "/MyFirstZnode";

        try {
            zooKeeper = zooKeeperConnection.connect("192.168.1.133");
            Stat stat = znodeExists(path);

            if (stat != null) {
                System.out.println("node Exists and the node version is" + stat.getVersion());
            } else {
                System.out.println("node dose not exist");
            }
            zooKeeperConnection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
