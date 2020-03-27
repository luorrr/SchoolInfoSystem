package com.nwnu.pojo;

/**
 * Filename: Manager.java
 * 
 * ·管理员数据
 * 
 * @author Luor
 * @version 1.0
 */
public class Manager {
	
	private String id;
	private String name;
	private String password;
	private String dept;
	
	/**
	 * ·构造函数
	 * 
	 * @param id
	 * @param name
	 * @param password
	 * @param dept
	 */
	public Manager(String id, String name, String password, String dept) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.dept = dept;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	@Override
	public String toString() {
		return id + "   " + name + "   " + dept + "\n";
	}
	
}
