package test.frame;

import java.awt.Component;
import javax.swing.JButton;

/**
  * @time：2019年12月26日 下午9:21:35
  * @author：zhoujian
  * @corporation：luke
  * @description：泛型类、方法测试
  * @finished：false
  * @finishTime：
  *
  */

/**
 * 泛型类参数可以用任何标识符来表示
 * 如一个时可以用T，两个时可以用K,V
 * */
public class GenericityTest<XD>{
	/**
	 * 泛型类参数可以作为方法类型
	 * 
	 * */
	public void printT(XD t) {
		//HashMap<K, V>;
	}
	
	
	/**
	 * 泛型方法测试：
	 * <? extends T> - E是T的子类
	 * <? super T> - <E super JComponent> - 我没有测试成功
	 * 
	 * 注意<? extends T> 的写法：
	 * 注：?可以等于T，即子类等于父类也可以。
	 * */
	public static <E extends Component> void printArray(E[] array) {
		
	}
	
	public static void main(String[] args) {
		
		//OK
		JButton[] buttons = new JButton[3];
		buttons[0] =new JButton();
		buttons[1] =new JButton();
		buttons[2] =new JButton();
		printArray(buttons);
		
		//NO: Bound mismatch
		/*String[] strings = new String[3];
		strings[0]= "1";
		strings[1] ="2";
		strings[2] ="3";
		printArray(strings);*/
	}

}
