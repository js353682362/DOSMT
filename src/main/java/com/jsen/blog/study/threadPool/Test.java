/*
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
package com.jsen.blog.study.threadPool;

import java.util.concurrent.CountDownLatch;

/**
 * @（#）:Test.java
 * @description:
 * @author: jiaosen 2018/4/17
 * @version: Version 1.0
 */
public class Test {
    static int i = 0;

    public static void main(String[] args) {
        final CountDownLatch cdl = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                cdl.countDown();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子进程" + Thread.currentThread().getName() + "正在执行");
                System.out.println("第" + Test.i++ + "个线程");
//                    Thread.sleep(10000);
                System.out.println("子进程" + Thread.currentThread().getName() + "执行完毕");

            }).start();
        }
        try {
            System.out.println("等待20个子线程执行完毕......");
            cdl.await();
            System.out.println("20个子线程执行完毕......");
            System.out.println("继续执行子线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程继续。。。。。。。");
    }
}
