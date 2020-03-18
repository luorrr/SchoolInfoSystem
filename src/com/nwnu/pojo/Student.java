package com.nwnu.pojo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

/**
 * Filename: Student.java
 * 
 * ·学生数据
 * 
 * @author Luor
 * @version 1.0
 */
public class Student {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	private String id;
	private String name;
	private String sex;
	private String college;
	private String major;
	private String phoneNumber;
	private Date recordDate;
	private String province;
	private String city;
	private String diagnosed;
	private BigDecimal temperature;
	private int count;
	
	/**
	 * ·构造函数
	 * 
	 * @param count
	 * @param recordDate
	 * @param province
	 */
	public Student(int count, Date recordDate, String province) {
		this.count = count;
		this.recordDate = recordDate;
		this.province = province;
	}
	
	/**
	 * ·构造函数
	 * 
	 * @param id
	 * @param name
	 * @param sex
	 * @param college
	 * @param major
	 * @param phoneNumber
	 * @param recordDate
	 * @param province
	 * @param city
	 * @param diagnosed
	 * @param temperature
	 */
	public Student(String id, String name, String sex, String college, String major, String phoneNumber, Date recordDate, 
			String province, String city, String diagnosed, BigDecimal temperature) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.college = college;
		this.major = major;
		this.phoneNumber = phoneNumber;
		this.recordDate = recordDate;
		this.province = province;
		this.city = city;
		this.diagnosed = diagnosed;
		this.temperature = temperature;
	}
	
	/**
	 * ·构造函数
	 * 
	 * @param id
	 * @param name
	 * @param sex
	 * @param college
	 * @param major
	 * @param phoneNumber
	 * @param recordDate
	 * @param province
	 * @param city
	 * @param diagnosed
	 * @param temperature
	 * @throws ParseException
	 */
	public Student(String id, String name, String sex, String college, String major, String phoneNumber, String recordDate, 
			String province, String city, String diagnosed, BigDecimal temperature) throws ParseException {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.college = college;
		this.major = major;
		this.phoneNumber = phoneNumber;
		Date date = new Date(sdf.parse(recordDate).getTime());
		this.recordDate = date;
		this.province = province;
		this.city = city;
		this.diagnosed = diagnosed;
		this.temperature = temperature;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDiagnosed() {
		return diagnosed;
	}
	public void setDiagnosed(String diagnosed) {
		this.diagnosed = diagnosed;
	}
	public BigDecimal getTemperature() {
		return temperature;
	}
	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		String isDiagnosed = "是";
		if (diagnosed.equals("0")) {
			isDiagnosed = "否";
		}
		return id + "   " + name + "   " + sex + "   " + college + "   " + major + "   " + phoneNumber + "   " + recordDate 
				+ "   " + province + "   " + city + "   " + isDiagnosed + "   " + temperature + "\n";
	}
	
}
