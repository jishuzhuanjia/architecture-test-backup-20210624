package com.zj.test.java.lang.thread;

/** 守护线程测试 */
public class DaemonThread {

	public static void main(String[] args) {
		Thread daemonThread = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					// 守护进程只输出有限随机次
					// 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
					// 说明守护进程就像是守护者一样，服务于用户线程，当所有用户线程都结束了，守护线程就没有存在的意义了，就会跟随jvm一起结束。
					System.out.println("daemon thread running");
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
	}
}
