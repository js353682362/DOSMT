package com.jsen.blog.study.net;/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                  `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @（#）:SocketServerM.java
 * @description:
 * @author: jiaosen 2018/4/23
 * @version: Version 1.0
 */
public class SocketServerM {
    public static void main(String[] args) throws IOException {
        int port = 7000;
        int clientNo = 1;
        ServerSocket serverSocket = new ServerSocket(7000);
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new SingleServer(socket, clientNo));
                clientNo++;
            }
        }finally {
            serverSocket.close();
        }
    }
}
