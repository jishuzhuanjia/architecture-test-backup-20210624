package com.zj.test.java.lang.thread;

/**
 *
 * 守护线程测试
 *
 * --------------------------------- NOTE -------------------------
 * 当JVM中运行的线程都是守护线程时，JVM会退出，这就意味着守护线程不能离开普通线程而单独存活，这也是为什么叫守护线程。
 *
 * 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就会一直工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
 * */
public class DaemonThreadTest {

    public static void main(String[] args) {

        // 守护线程
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Daemon Thread is Running");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // 必须在start之前调用,在start方法调用之后设置无效。
        daemonThread.setDaemon(true);
        daemonThread.start();

        // 主线程等待一会，否则可能无法看到守护线程输出的内容
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 主线程执行完，因为这里没有其他的非守护线程,所以守护线程也会结束,jvm退出。
        // 如果daemonThread是正常的线程，主线程结束后jvm不会退出，daemonThread会继续执行。
    }
}