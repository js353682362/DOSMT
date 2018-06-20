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
import java.net.Socket;
import java.util.Scanner;

/**
 * @（#）:SocketClientTest.java
 * @description:
 * @author: jiaosen 2018/4/23
 * @version: Version 1.0
 */
public class SocketClientTest {
    public static void main(String[] args) throws IOException {
        int port = 7000;
        String host = "localhost";
        Socket socket = new Socket(host, port);
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        Scanner sc = new Scanner(System.in);

        boolean flag = false;

        while (!flag){
            System.out.println("请输入正方形的长");
            double length = sc.nextDouble();
            dataOutputStream.writeDouble(length);
            dataOutputStream.flush();
            double area = dataInputStream.readDouble();
            System.out.println("返回的计算结果" + area);
            while (true){
                System.out.println("继续计算?(Y/N)");
                String str = sc.next();
                if(str.equalsIgnoreCase("N")){
                    dataOutputStream.writeInt(0);
                    dataOutputStream.flush();
                    flag = true;
                    break;
                } else if(str.equalsIgnoreCase("Y")){
                    dataOutputStream.writeInt(1);
                    dataOutputStream.flush();
                    break;
                }
            }
        }
        socket.close();
    }
}
