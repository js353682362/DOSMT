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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @（#）:SocketServerTest.java
 * @description:
 * @author: jiaosen 2018/4/23
 * @version: Version 1.0
 */
public class SocketServerTest {
    public static void main(String[] args) throws IOException {
        int port = 7000;
        ServerSocket serverSocket = new ServerSocket(7000);
        Socket socket = serverSocket.accept();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        do {
//            double length = dataInputStream.readDouble();
            byte[] bytes = new byte[1024];
            while (dataInputStream.read(bytes) != -1){
                System.out.println(new String(bytes,"UTF-8"));
            }
//            System.out.println("服务器端接收数据长度为:" + length);
//            double result = length * length;
            dataOutputStream.writeChars("德玛西亚");
            dataOutputStream.flush();
        } while (dataInputStream.readInt() != 0);
        socket.close();
        serverSocket.close();
    }
}
