package ssm.test.controller;

public class User {

	String username;
	
	int age;

	public String getUsername() {
		return username;
	}

	public int getAge() {
		return age;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return username + "," +age;
	}
}
