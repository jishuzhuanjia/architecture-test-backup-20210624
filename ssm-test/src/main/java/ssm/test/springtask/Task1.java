package ssm.test.springtask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @time 2019年12月26日 下午4:45:08
 * @author Administrator
 * @corporation luku
 * @description: 任务调度作业类
 */
@Service

public class Task1 {
	
	/**
	 * 测试task scheduler注解式实现
	 * 表达式：ss mm hh dd mm weekday
	 * 
	 * success：是
	 * 
	 * 结论：可能会影响服务器正常关闭
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
}



