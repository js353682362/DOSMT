package com.jsen.blog.study.netty.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @（#）:SocketServerRunnable.java
 * @description:
 * @author: jiaosen 2018/6/20
 * @version: Version 1.0
 */
public class SocketServerRunnable implements Runnable {

    private SocketChannel socketChannel;

    private String tag;

    public SocketServerRunnable(SocketChannel socketChannel, String tag) {
        this.socketChannel = socketChannel;
        this.tag = tag;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();

        try {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            buffer.clear();
            int readNum = socketChannel.read(buffer);
            System.out.println(tag + " receive: " + byteBufferToString(buffer));
            buffer.rewind();
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

}
