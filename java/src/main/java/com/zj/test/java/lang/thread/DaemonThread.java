package com.zj.test.java.lang.thread;

/** 守护线程测试 */
public class DaemonThread {

	public static void main(String[] args) {
		Thread daemonThread = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					//守护进程只输出有限随机次
					// 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
					// 说明守护进程就像是守护者一样，服务于用户线程，当所有用户线程都结束了，守护线程就没有存在的意义了，就会跟随jvm一起结束。
					System.out.println("daemon thread running");
					/*try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}
			}

		});

		//将线程设置为守护进程或者用户进程(正常线程),当执行的进程全部是守护进程,javaw虚拟机会退出
		daemonThread.setDaemon(true);
		daemonThread.start();
	}
}
