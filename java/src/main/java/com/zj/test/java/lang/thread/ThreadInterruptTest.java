/**
 * ThreadTest.java
 * Creation time:2018年12月10日 下午1:57:23
 * Author:zhoujian
 * QQ:2025513
 * e-mail:www.mfcdebugger@163.com
 */
package com.zj.test.java.lang.thread;

import com.zj.test.java.util.ZJSwingUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 2020年11月18日 16:36:20
 *
 * 本文件重在分享学习成果，不在于代码，不保证代码能够运行
 *
 *
 * 1.线程状态
 * 新建(NEW),就绪/可运行状态(RUNNABLE),运行(RUNNING),阻塞(BLOCKED),死亡(DEAD)
 *
 * 2.线程的生命周期
 *                       新建/初始状态
 *                          (New)
 *                           |
 *                      t.start()
 *                           |
 *                           |
 *                            ↓
 *                          就绪
 *                       (Runnable)
 *        t.sleep结束   ↗    ↑   |
 *            t2终止 ↗       |   |
 *     用户输入完毕 ↗         |   |
 *              ↗           |   |
 *   阻塞状态  ↗              |   |
 *  (Blocked)               |   |
 *         ↖        时间片用完|   |os 调度: 线程获取cpu时间片
 *   t.sleep ↖              |   |
 *   t2.join   ↖            |   |
 *   I/O等待用户输入↖          |   |
 *                  ↖        |   ↓
 *                     ↖     运行状态
 *                        ↖ (Running)
 *                             ↓
 *                             ↓ run()或main()执行完毕
 *                             ↓
 *                           死亡状态
 *                           (DEAD)
 *
 * 已经死亡的线程不能重新start()
 *
 * 3.向线程传递参数的方式
 * 1.构造函数 - 被动
 * 2.属性和方法 - 被动
 * 3.回调函数，接口。 - 对于线程是主动的
 *
 * 4.线程返回结果的方式
 * 1.属性和方法,但是这个存在同步问题。
 * 2.join()。
 * 3.回调函数，接口。
 *
 * 5.Thread常用方法:
 * public final void setPriority(int newPriority)
 * 线程的优先级别受线程组优先级别的影响。1-10,不同的操作系统可能映射不同的优先级等级
 * 更改线程的优先级，首先,不带参数调用该线程的checkAccess方法。这可能会导致抛出SecurityException。
 * 否则,该线程的优先级将被设置为newPriority以及该线程的线程组的最大允许优先级中较小的一个。
 *
 *
 * public final synchronized void setName(String name)
 * 设置线程的名字,如果调用了线程的默认无参数的构造函数,之后再调用setName修改线程的名字,
 * 则线程将会占用之前默认构造函数分配的Thread-N名字,这就意味着这个名字不会分配给其他的线程。
 *
 *
 * public final void setDaemon(boolean on)
 * 设置线程为守护线程或者用户线程
 * 这个方法首先调用isAlive,如果为true意味着线程已经开始还并没有死亡,则会抛出状态异常，
 * 当惟一运行的线程是守护进程线程时，Java虚拟机将退出。
 * 必须在启动线程之前调用此方法。
 *
 *
 * public final synchronized void join(long millis)
 * public final synchronized void join(long millis, int nanos)
 * 等待/同步执行函数，如果指定的时间内,目标线程没有执行结束，本线程将会停止等待,并继续执行下面的代码。
 * 第二个参数纳秒,有效值: 0~999999
 * 等待线程死亡的时间最多为千分之一毫秒。？？？？？？？？？？？？？？？？？？0意味着永远等待。
 * 这个实现使用这个循环。基于此条件的等待调用。当线程终止此操作时。调用notifyAll方法。
 * 建议应用程序不要在线程实例上使用wait、notify或notifyAll。
 *
 *
 * public long getId()
 * 返回该线程的标识符。线程ID是创建该线程时生成的正数。线程ID是惟一的，
 * 在其生命周期内保持不变。当线程终止时，可以重用该线程ID。
 *
 *
 * public final String getName()
 *
 *
 * public final int getPriority()
 * 获取优先级别，默认为5
 *
 *
 * public final native boolean isAlive()
 * 测试该线程是否存活。如果线程已经启动，但尚未死亡，则该线程为活动线程。
 *
 *
 * public final ThreadGroup getThreadGroup()
 * 获取线程所在线程组
 *
 *
 * public void interrupt()
 *
 *
 * 状态操作函数：
 * start()  开始就绪
 * suspend()    挂起
 * unsafe resume()  恢复
 * unsafe stop()    停止
 *
 *
 * public static native void sleep(long millis)
 * public static void sleep(long millis, int nanos)
 *
 *
 * public static native void yield()
 * 给调度器的提示，即当前线程愿意放弃当前对处理器的使用。调度器可以忽略这个提示。
 *
 *
 * 关于线程打断的三个函数:
 * Thread.interrupted() - 返回线程是否是打断状态,并清除打断标记。
 *
 * thread.interrupt() - 打断当前状态,并将标记置true, 如果线程当前处理阻塞状态,则会打断阻塞，并抛出
 *                      InterruptException异常并且将标记清除。
 *
 * thread.isInterrupted() - 获取当前的是否是打断状态,不会改变打断的状态。
 */

/*
 * volatile关键字 用于简单类型变量 只有当变量操作与自身无关才是原子操作              ？？？？？？？？？？？？？？？
 */
@SuppressWarnings("unused")
public class ThreadInterruptTest {

    /***/
    public static void main(String[] args) {
        JFrame tf = ZJSwingUtilities.createTestJFrame("thread test", 800, 600);

        tf.setLayout(new FlowLayout());

        // 操作的线程
        CountThread ct1 = new CountThread();

        JButton buttonInterrupt = new JButton("打断");
        JButton buttonIncPriority = new JButton("增加优先级");
        JButton buttonDecPriority = new JButton("减少优先级别");
        buttonDecPriority.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Thread.MIN_PRIORITY
                if (ct1.getPriority() > Thread.MIN_PRIORITY) {
                    ct1.setPriority(ct1.getPriority() - 1);
                }
            }

        });

        buttonIncPriority.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Thread.MAX_PRIORITY
                if (ct1.getPriority() < Thread.MAX_PRIORITY) {
                    ct1.setPriority(ct1.getPriority() + 1);
                }
            }

        });
        tf.add(buttonIncPriority);
        tf.add(buttonDecPriority);
        buttonInterrupt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                /** 中断 */
                // 可以用来打断当前状态（如sleep,join），并设置标识。如果处于sleep，则结束sleep状态并抛出异常。
                /*
                 * interrupt()--设置线程为中断状态,可以打断线程的当前状态 如果线程处于中断状态则结束中断状态,如在调用sleep,wait,join等方法后,
                 * 并置中断标识为false,如果不是,则置中断 标识为true,这个操作不会影响线程的执行,但是可以利用这个标识。
                 *
                 * 来自翻译: 除非当前线程本身正在中断(通常是允许的),否则将调用该线程的checkAccess方法,
                 * 这可能导致抛出SecurityException。如果该线程在调用对象类的wait()、wait(long)或join()、 join(long,
                 * int)、join(long, long)、sleep(long)或sleep(long, int)方法时被阻塞,
                 * 那么它的中断状态将被清除,它将接收InterruptedException,并终止中断状态。
                 * 如果这个线程在一个InterruptibleChannel上的I/O操作中被阻塞,那么这个通道将被关闭,
                 * 线程的中断状态将被设置,线程将接收一个java.nio.channels.ClosedByInterruptException。
                 */

                // 测试线程是否被打断,这个方法不会影响打断状态
                if (!ct1.isInterrupted()) {

                    /*
                     * 1.如果线程处理阻塞状态,sleep,join,wait等, 首先将interrupted=true,然后会抛出InterruptException,
                     * 在抛出异常后,会终止当前的线程状态(如sleep),然后将interruped=false,线程继续执行。
                     * 2.如果线程在正常的运行状态,则只是将标记置为true
                     */
                    ct1.interrupt();
                    // true
                    System.out.println("当前打断状态是:" + ct1.isInterrupted());

                }

                // System.out.println(ct1.isInterrupted());

                /*
                 *程序暂停、停止、取消 暂停：
                 *1. 通过中断标识来执行操作, 或者在InterruptedException中调用对象.wait
                 * 2. suspend
                 * //安全问题
                 *
                 *停止 stop //问题 在start后程序就不能手动停止,除了stop
                 *
                 *取消 中断标识符直接return出去
                 *
                 */
            }

        });
        tf.add(buttonInterrupt);
        tf.setVisible(true);

        // 10
        System.out.println(ct1.getThreadGroup().getMaxPriority());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // ct1.setPriority(6);

        ct1.start();
    }
}

// 一个数数的线程,从0-1000000000
class CountThread extends Thread {
    int count;

    @Override
    public void run() {

        for (; count <= 1000000000; count++) {

            /*
             * try { Thread.sleep(2000); } catch (InterruptedException e1) { // TODO
             * Auto-generated catch block e1.printStackTrace(); }
             *
             * System.out.println("sleep 打断以后的状态:" + isInterrupted());
             */

            // 返回线程当前是否中断
            // System.out.println(isInterrupted());
            if (!isInterrupted()) {
                // 线程当前优先级:5
                // System.out.println("线程当前优先级:" + this.getPriority());
                System.out.println(count);

                /*
 *try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) { // TODO
 *Auto - generated catch block //System.out.println("Sleep状态下的打断状态: " +
                            * this.isInterrupted());
                    e1.printStackTrace(); //System.exit(0); }
 */

            } else {
                // 清除打断标记，并返回上一次的状态。
                // 如果线程当前是打断状态,则标记置为false 并返回true,否则返回false
                // 进入此else逻辑，说明线程当前已经打断，但是执行下面的语句后会清除打断状态。
                // boolean isInterrupted = Thread.interrupted();

                // true
                // System.out.println("线程当前是否被打断:" + isInterrupted);

                // false: 说明Thread,interrupted()会取消打断状态
                System.out.println("打断标记:  " + this.isInterrupted());

                /**
                 *调用阻塞方法如sleep() 时，必须首先清除打断记号，否则不会睡眠，而会直接抛出InterruptedException e1
                 *
                 *sleep() 的调用不会使interrupted == true
                 *
                 *thread.interrupt() 对Thread.sleep() 的影响：
                 *首先将interrupted设置为true，并在抛出异常之前，将interrupted标记清空，因此此时catch {
                 }
                 语句中:
                 *thread.isInterrupted() == false
                 *
                 *thread.interrupt() 对正常运行的影响：只是设置interrupted为true。
                 */
                // true,并将interrupted==false
                System.out.println(Thread.interrupted());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("Sleep状态下的打断状态: " + this.isInterrupted());
                    e1.printStackTrace();
                    System.exit(0);
                }

                System.out.println("after sleep");
            }
        }
    }
}

