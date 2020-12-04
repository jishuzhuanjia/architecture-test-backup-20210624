package com.zj.test.java.lang.thread;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/12/4 17:14
 * @description: 线程通信方式之管道流Demo
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ThreadPipeCommunicateDemo {

    class MessageSender implements Runnable {
        PipedOutputStream pipedOutputStream;

        public MessageSender() {
            pipedOutputStream = new PipedOutputStream();
        }

        public PipedOutputStream getPipedOutputStream() {
            return pipedOutputStream;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // 向管道发送消息
                    pipedOutputStream.write((Thread.currentThread().getName() + ": " + "Hello," + new Date()).getBytes());

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class MessageReceiver implements Runnable {

        // 同
        PipedInputStream pipedInputStream;

        public MessageReceiver() {
            pipedInputStream = new PipedInputStream();
        }

        public PipedInputStream getPipedInputStream() {
            return pipedInputStream;
        }

        @Override
        public void run() {
            byte[] b = new byte[1024];
            int len;
            // 一直监听消息
            while (true) {
                try {
                    /*
                    读取情况
                    1.如果写线程写完并关闭PipeOutputStream，写线程死亡，则读线程read()不会报错，会返回实际读取的数据量，如果没有数据返回-1。

                    2.如果写线程写完没有关闭PipeOutputStream并线程结束（死亡），则读线程会首先读取写线程写出的数据，当读取不到数据的时候，发现写线程已经死亡，
                    报错：java.io.IOException: write end dead

                    read是一个阻塞的方法，如果流没有关闭且写线程没有结束，它会一直等待输入。

                     */
                    len = pipedInputStream.read(b);

                    if (len > -1)
                        TestHelper.println("收到新信息", new String(b, 0, len));
                    else {
                        TestHelper.println("没有数据");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 1.管道流通信demo
     *
     * 【结论】
     * 1.一个管道输入流只能连接一个管道输出流。否则：
     * java.io.IOException: Already connected
     *
     * 【优点】
     * 1.管道输入流PipeInputStream的读取方法是阻塞的，比起while轮询，性能会好很多。
     */
    @Test
    public void test1() {

        MessageReceiver messageReceiver = new MessageReceiver();

        MessageSender messageSender = new MessageSender();

        try {
            // 管道流建立连接
            messageSender.getPipedOutputStream().connect(messageReceiver.getPipedInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        一个PipeInputStream只能连接一个PipeOutputStream，否则报错：
        java.io.IOException: Already connected
         */
        /* MessageSender messageSender1 = new MessageSender();
        try {
            messageSender1.getPipedOutputStream().connect(messageReceiver.getPipedInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // 启动消息发送线程，并发送消息
        new Thread(messageSender).start();

        // 等待发送者死亡（即运行结束）
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动消息监听线程
        new Thread(messageReceiver).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
