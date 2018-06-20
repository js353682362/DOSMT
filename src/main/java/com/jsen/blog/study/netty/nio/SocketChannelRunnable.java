package com.jsen.blog.study.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @（#）:SocketChannelRunnable.java
 * @description:
 * @author: jiaosen 2018/6/20
 * @version: Version 1.0
 */
public class SocketChannelRunnable implements Runnable {
    private String tag = "default";

    public SocketChannelRunnable(){}

    public SocketChannelRunnable(String tag) {
        this.tag = tag;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9078));

            System.out.println(tag + " connect success ......");

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("hello world".getBytes("utf-8"));
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }

            byteBuffer.clear();
            socketChannel.read(byteBuffer);

            System.out.println(tag + " receive: " + byteBufferToString(byteBuffer));

            socketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(tag + " time: " + (System.currentTimeMillis() - start));
        }
    }
    public String byteBufferToString(ByteBuffer byteBuffer) {
        Charset charset = null;
        CharsetDecoder charsetDecoder = null;

        CharBuffer charBuffer = null;
        try {
            charset = Charset.forName("utf-8");
            charsetDecoder = charset.newDecoder();
            //用这个的话，只能输出来一次结果，第二次显示为空
            //charBuffer = charsetDecoder.decode(byteBuffer);
            charBuffer = charsetDecoder.decode(byteBuffer.asReadOnlyBuffer());
            return charBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) throws IOException {
        new SocketChannelRunnable().run();
    }
}