package test;

/* *
 * @time：2020年2月13日 下午5:49:31
 * @Author：zhoujian
 * @QQ：2025513
 * @description：
 */

public class FindUppercase {
	
	
	public static boolean findUpperChar(String s,char findChar) {
		//// 如果输入小写字符自动转换成大小
		if(findChar>=97)
			findChar -=32;
		
		boolean found = false;

		for (char c : s.toCharArray()) {
			if (c == findChar) {
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	public static void main(String[] args) {
		byte b = 'a';
		System.out.println(b);
		System.out.println((int)'A');
		//true
		System.out.println(findUpperChar("jsdhlD", 'd'));
	}
}
