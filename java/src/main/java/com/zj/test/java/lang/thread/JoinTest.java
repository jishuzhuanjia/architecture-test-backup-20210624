/**
 *Test.java
 *Creation time:2018年12月11日 上午9:53:18
 *Author:zhoujian
 *QQ:2025513
 *e-mail:www.mfcdebugger@163.com
 */

package com.zj.test.java.lang.thread;

/** 线程join()方法测试 */
public class JoinTest {

	/***/
	public static void main(String[] args) {
		/*Thread t1 = new Thread() {

			@Override
			public void run() {
				super.run();
			}

		};

		t1.setPriority(6);
		System.out.println(t1.getPriority());*/

		Thread subThread = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i =0;i<100000;i++) {
					System.out.println(i);
					/*try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}
			}

		});

		subThread.start();

		try {
			//在一个线程中调用另外一个线程对象的join方法
			//实现是通过thread.isAlive() 和 wait()
			//推荐不要对线程实例使用wait(),notify 和 notifyAll();
			//如果线程没有调用start()则无操作。
			//如果线程已经开始,等待指定时间后会继续执行下面的代码
			//单位  ms
			//1ms = 1000000ns
			subThread.join(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*将会等待线程执行完成try {
			subThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		System.out.println("the sub thread is finished!");

		//死亡状态的线程不能重新开始
		//否则Exception in thread "main" java.lang.IllegalThreadStateException
		//subThread.start();

	}

}
