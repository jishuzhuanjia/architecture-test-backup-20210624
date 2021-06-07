package test.frame.spring.springtask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @time  2019年12月26日 下午4:45:08
 * @author  zhoujian
 * @corporation  luku
 * @description  任务调度测试，@Scheduled注解详细说明
 */
@Service
public class Task1 {
	
	/**
	 * 测试任务调度注解式实现：@Schedule
	 * 
	 * success：是
	 * 
	 * Cron expression must consist of 6 fields (found 7 in "0 27 17 26 12 ? 2019")
	 * 也就是说不支持year
	 * */
	@Scheduled(cron="0 27 17 26 12 ?")
	public void startWork() {
		int i = 0;
		while(true) {
			System.out.println(i);
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// @Scheduled(cron="* */1 * * * ?")  -  这种写法不能实现每分钟执行一次，正确写法如下：
	
	//  */1解释：你也可以使用*/y，它等同于0/y,并不是说，必须等到0分才会执行，而是可以任何分钟值都可以。但是这种写法需要指定秒才能每分钟执行。
	//@Scheduled(cron="0 */1 * * * ?")
	
	/**
	 * <pre>
	 * cron表达式的使用，并测试是否多线程和 '* /1',的使用
	 * <ul>
	 * <li>@Scheduled注解的任务，是单线程的。</li>
	 * 
	 * <li> * /1解释：你也可以使用* /y，它等同于0 /y,并不是说，必须等到0分才会执行，而是可以任何分钟值都可以。但是这种写法需要指定秒才能执行。</li>
	 * </ul>
	 * <pre>
	 **/
	@Scheduled(cron="* * * * * ?")
	public void startWork1() {
		System.out.println("Work1");
		try {
			//  注意：如果cron表达式指定每秒执行，则只有等上一次任务完成，才能继续执行下一次方法。
			//  因为，执行方法的不是多线程。
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 
	 * 如果不指定cron及fixed*属性，则立刻执行一次
	 * 
	 * fixedDelay：上个任务执行完毕，隔多久再次执行
	 *
	 * 'cron', fixedDelay,'fixedDelay(String)', or 'fixedRate(String)'...属性，只能出现一个，因此使用 fixedDelay，则方法会立刻执行一次。
	  *@Scheduled(fixedDelay=10000)
	 * 
	 */
	
	/**
	 * 实现固定间隔执行：以下写法可以实现spring初始化后，延时10秒再执行任务，任务执行完后5秒后再次执行任务。
	 * 
	 * <pre>
	 * <strong>NOTE:</strong>
	 * fixedDelay/fixedDelayString：上一次任务完成后到下一次任务开始的间隔，区别在于字符串
	 * 
	 * fixedRate/fixedRateString：上一次任务开始到下一次任务开始的间隔，区别在于字符串
	 * 
	 * initialDelay/ initialDelayString：任务开始前，初始延时。
	 * 
	 * zone：时区，接收一个java.util.TimeZone#ID。cron表达式 会基于该时区解析。默认是一个空字符串，即取服务器所在地的时区。比如我们一般使用的时区Asia/Shanghai。该字段我们一般留空。
	 * </pre>
	 * */
	@Scheduled(fixedDelayString=("5000"),initialDelay=10000)
	public void startWork2() {
		System.out.println("entering Work2");
	}
	
}