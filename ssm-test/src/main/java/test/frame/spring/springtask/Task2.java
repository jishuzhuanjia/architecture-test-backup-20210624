package test.frame.spring.springtask;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 探究cron表达式在Java中的写法
 */
/*@Service("task2")*/
@Component("Task2")
public class Task2 {
	private static int count ;
	/**java中cron表达式可选值还是人性化的
	 * W：0为周日,1~6对应周一到周六
	 * M：1~12
	 * D:从1开始
	 * H:0~23
	 * M：0~59
	 * S：0~59
	 * 
	 * 
	 * 灵活使用:
	 * * -->  用在所有字段
	 * , --> 用来连接列表值
	 * ? --> 只在日期和星期中使用
	 * -  --> 表示一个范围
	 * / --> 等步长序列,如在分钟字段中使用0/15，则表示为0,15,30和45秒，而5/15在分钟字段中表示5,20,35,50，你也可以使用* /y，它等同于0/y；
	 * W: 用在日期，表示离当日最近的工作日,会往前往后推算工作日,得出最近的一天。但是当处在月交替期间,不能跨月,会选择当月的某天。
	 * L: 用于日期/星期 -> 用在日期表示本月的最后一天,用在星期表示周6,指的是当月所有的周6,如果在L前面有数值,如5L,则表示本月最后一个周五才会触发。
	 * LW: 用于日期 --> LW表示最后一个工作日
	 * #: 只用于星期 --> 4#5表示当月的第五个星期5，java中周日用0表示
	 * C: 用于日期/星期  --> 例如5C在日期字段中就相当于日历5日以后的第一天。0C在星期字段中相当于星期日后的第一天。
	 * */
	@Scheduled(cron="0 53 9 * * 6")
	public void startWork() {
		System.out.println("Task2 count: " + count);
		count++;
	}
	
	@Test
	public void test1() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("resource/applicationContext.xml");
		System.out.println("after");
		classPathXmlApplicationContext.close();
	}
}