package com.zj.test.java.lang.thread;

import com.zj.test.util.TestHelper;

/**
 * author: zhoujian
 * corporation: none
 * create-time: 2020年8月25日 下午12:15:35
 * description: object.wait()测试
 * finished: 0
 */
// 车票池
class TicketPool implements Runnable {

    /**
     * 剩余票数
     */
    int tickCount = 300;

    /**
     * 锁
     */
    Object lock = new Object();

    /**
     * 测试： 假设每个售票机线程，售票100张。然后使用wait()放弃锁，看看wait对线程的影响。
     *
     * 测试结果总结：wait会放弃锁，并且有以下特性：
     * 1.如果synchronized修饰的是代码块，则代码块中wait()后面的代码不会执行。且synchronized代码块后的代码也不会执行。即：
     * public void methodName() {
     * synchronized(lock){
     * ... lock.wait();
     * ... // code fter wait(),Cannot reach
     * }
     * ... // code after synchronized code block,Cannot reach
     * } // 但是当通过notify或notifyAll重新获得锁后，没执行的代码会被执行。
     *
     * 2.如果synchronized修饰的是方法，则wait()方法后面的代码不会执行。即
     * public synchronized void methodName(){
     * ...
     * this.wait()
     * ... // code after wait(),Cannot reach } // 但是当通过notify或notifyAll重新获得锁后，没执行的代码会被执行。
     *
     * 3.调用wait()会通知jvm唤醒某个竞争该对象锁的线程X。唤醒的是那些正常状态的线程，而不是执行wait()进入睡眠的程序。
     * 调用wait()后的线程，线程会一直等待，不会主动抢夺锁，除非其他线程调用notify或notifyAll。
     */
    @Override
    public void run() {

        /** wait(),notify(),notifyAll()都需要在synchronized作用范围内使用 */
		/*try {
			 // Exception in thread "Thread-0" Exception in thread "Thread-1" Exception in thread "Thread-2" java.lang.IllegalMonitorStateException
			lock.wait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

        // Exception in thread "Thread-2" Exception in thread "Thread-0" Exception in thread "Thread-1" java.lang.IllegalMonitorStateException
        //lock.notify();

        // Exception in thread "Thread-0" Exception in thread "Thread-2" Exception in thread "Thread-1" java.lang.IllegalMonitorStateException
        // lock.notifyAll();


        while (true) {
            System.out.println(Thread.currentThread().getName() + " code before synchronized");

            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    tickCount--;
                    TestHelper.println(Thread.currentThread().getName() + "总售票数: " + (i + 1));
                    if(tickCount==0){
                        TestHelper.println("票已卖完...");
                        System.exit(0);
                    }
                    // 降低卖票速度，便于观察控制台
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 某售票机线程持续卖票100张,会进入等待
                    if (i == 99) {
                        TestHelper.println(Thread.currentThread().getName() + ": going to wait...");
                        try {
                            /**
                             * 唤醒某个睡眠的线程获取锁
                             * 注意：因为wait()后面的代码不会执行，所以notify()应在wait()之前。
                             *
                             * 4.notify()和notifyAll()
                             * 两者都是唤醒因调用wait()而睡眠的线程进行cpu资源的抢夺,不包括正常抢夺锁的线程。 不同点：
                             * notify：唤醒某个睡眠线程，本线程释放锁后直接将锁分配给这个线程。因此正常抢夺的线程将会继续等待。这也就是说只有因调用wait()而睡眠的线程，才有获得锁的机会。
                             * 缺点: 对于正常抢夺资源的线程不公平。
                             *
                             * notifyAll：唤醒所有睡眠线程，本线程释放锁后，根据算法将锁分配到某个线程，这也就是意味着调用该方法后，所有正常抢占锁和因调用wait()而睡眠的线程，都有得到锁的机会。
                             * 优点：对于线程抢夺CPU资源而言，对因为调用wait而等待的线程和正常抢夺状态的线程而言，相对公平。
                             */
                            lock.notify();
                            // lock.notifyAll();

                            /**
                             * 释放锁，立即释放cpu资源，并根据算法使得某个竞争锁的线程获取锁并开始执行 本线程会一直等待，不再主动获取锁。
                             */
                            lock.wait();

                            /**
                             * 5.wait,notify,notifyAll优缺点：
                             *
                             * 优点：
                             * 1.弥补synchronized无法手动释放锁、分配锁的缺陷。
                             *
                             * 2.控制synchronized代码块的执行进度：
                             * synchronized碎片化，synchronized块作为一个整体,当某个线程获取锁后，会将synchronized块整个执行一遍。而wait的使用可中断synchronized块中代码的执行。
                             * 控制synchronized块的执行进程。如暂停，Thread.sleep()虽然可以达到效果，但是会一直占用锁，其他线程无法继续获取锁。而wait()则可以解决此问题，但是需要在合适的地方调用
                             * notify/notifyAll来恢复线程的执行。
                             *
                             * 3.增强对锁获取的控制: wait,notify,notifyAll的组合使用，可用来指定哪些线程能够获取锁，哪些线程不能。
                             *
                             * 缺点： 1.使用不当会导致同步问题。
                             */

                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ": after wait");
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": after for");
            }
            System.out.println(Thread.currentThread().getName() + ": after synchronized");
        }
    }
}

public class WaitNotifyNotifyAllTest {

    /**
     * wait：表示持有对象锁的线程A准备释放对象锁权限，释放cpu资源并进入等待。
     */

    public static void main(String[] args) {
        Runnable runnable = new TicketPool();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
