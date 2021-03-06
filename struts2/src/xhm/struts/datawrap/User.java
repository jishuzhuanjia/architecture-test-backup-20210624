package xhm.struts.datawrap;

public class User {
	
	
 public String name;
	
	//struts2 自动完成字符串到数值的转换
	public int age;
	
	 public String sex;
	
	public String getName() {
		return name;
	}



	public int getAge() {
		return age;
	}



	public String getSex() {
		return sex;
	}



	public void setName(String name) {
		this.name = name;
	}


	
	public void setAge(int age) {
		this.age = age;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName() + getSex() + getAge();
	}

}
