package com.zj.util.string;

/**
 * @time  2019年12月19日 上午9:57:22
 * @author  zhoujian
 * @corporation  luku
 * @description  字符串工具类
 * @finished  true
 * @finishTime  2019年12月27日 17:20:46
 */
public class StringUtils {

	/**
	 * <p>
	 * common-lang.jar下的isNumberic方法只能检测整型字符串，而不能检测浮点型 此方法用来实现浮点字符串的判断
	 * </p>
	 * <pre>
	 * NOTE:
	 * isDouble("  2.5  ")    =     true
	 * isDouble("  2. 5  ")    =    false
	 * isDouble("a2.5")    =    false
	 *  isDouble("2.5a")    =    false
	 *  </pre>
	 * 
	 * @param string 空格只允许出现在字符串两端，而不能在字符串之间。两端的空白会被忽略。
	 * 
	 * @return 
	 */
	public static boolean isDouble(String string) {
		if (string == null || string.equals(""))
			return false;

		try {
			Double.parseDouble(string);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(isDouble(" dsad "));
	}
}
