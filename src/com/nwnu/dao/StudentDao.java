package com.nwnu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nwnu.dao.DbUtil;
import com.nwnu.pojo.Student;

/**
 * Filename: StudentDao.java
 * 
 * ·查询学生信息
 * 
 * @author Luor
 * @version 1.0
 */
public class StudentDao {
	
	/**
	 * ·插入学生数据
	 * 
	 * @param s
	 * @return 执行结果
	 * @throws SQLException
	 */
	public boolean addStudent(Student s) throws SQLException {
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "Insert into studentinfo(id,name,sex,college,major,phoneNumber,recordDate,province,city,diagnosed,temperature)"
				+"values(?,?,?,?,?,?,?,?,?,?,?)";
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//传参
		ptmt.setString(1, s.getId());
		ptmt.setString(2, s.getName());
		ptmt.setString(3, s.getSex());
		ptmt.setString(4, s.getCollege());
		ptmt.setString(5, s.getMajor());
		ptmt.setString(6, s.getPhoneNumber());
		ptmt.setDate(7, s.getRecordDate());
		ptmt.setString(8, s.getProvince());
		ptmt.setString(9, s.getCity());
		ptmt.setString(10, s.getDiagnosed());
		ptmt.setBigDecimal(11, s.getTemperature());
		//执行
		try {
			ptmt.execute();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	
	/**
	 * ·通过学号查询信息
	 * 
	 * @param id
	 * @return 学生列表
	 * @throws SQLException
	 */
	public List<Student> quaryById(String id) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select * from studentinfo where id = " + id;
		//执行
		ResultSet rs = stmt.executeQuery(sql);
		//添加用户信息
		List<Student> stuList = new ArrayList<Student>();
		Student s = null;
		while(rs.next()) {
			s = new Student(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("major"), rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
			stuList.add(s);
		}
		return stuList;
	}
	
	/**
	 * ·通过学号与日期查询数据
	 * 
	 * @param id
	 * @param recordDate
	 * @return 学生实体
	 * @throws SQLException
	 */
	public Student findStudentInfo(String id, Date recordDate) throws SQLException {
		Student s = null;
		//获取数据库连接
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "select * from studentinfo where id = ? and recordDate = ?";
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//传参
		ptmt.setString(1, id);
		ptmt.setDate(2, recordDate);
		//执行
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			s = new Student(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("major"), rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
		}
		return s;
	}
	
	/**
	 * ·通过起始日期与截止日期查询数据
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @param isProvince
	 * @return 学生信息列表
	 * @throws SQLException
	 */
	public List<Student> quaryByDate(Date dateBegin, Date dateEnd, boolean isProvince) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "";
		if (isProvince) {
			sql = "select count(*) count,province from studentinfo where recordDate between '" + dateBegin + "' and '" + 
					dateEnd + "' and diagnosed = '1' Group By province;";
		} else {
			sql = "select count(*) count,recordDate from studentinfo where recordDate between '" + dateBegin + "' and '" + 
					dateEnd + "' and diagnosed = '1' Group By recordDate;";
		}
		//执行
		ResultSet rs = stmt.executeQuery(sql);
		//添加用户信息
		List<Student> stuList = new ArrayList<Student>();
		Student s = null;
		while(rs.next()) {
			if (isProvince) {
				s = new Student(rs.getInt("count"), null, rs.getString("province"));
			} else {
				s = new Student(rs.getInt("count"), rs.getDate("recordDate"), null);
			}
			stuList.add(s);
		}
		return stuList;
	}
	
	/**
	 * ·根据学院与日期信息查询学生数据
	 * 
	 * @param college
	 * @param date
	 * @return 学生信息列表
	 * @throws SQLException
	 */
	public List<Student> quaryByDeptAndDate(String college, Date date) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select * from studentinfo where college = '" + college + "' and recordDate = '" + date + "'";
		if (college.equalsIgnoreCase("西北师范大学")) {
			sql = "select * from studentinfo where recordDate = '" + date + "'";
		}
		//执行
		ResultSet rs = stmt.executeQuery(sql);
		//添加用户信息
		List<Student> stuList = new ArrayList<Student>();
		Student s = null;
		while(rs.next()) {
			s = new Student(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("major"), rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
			stuList.add(s);
		}
		return stuList;
	}
	
	/**
	 * ·根据学院信息查询学生数据
	 * ·注：此查询方法需修改MySQL的默认模式，即去除select @@global.sql_mode查询中的ONLY_FULL_GROUP_BY
	 * ·注：使用set @@global.sql_mode = ''方法
	 * 
	 * @param college
	 * @return 学生信息列表
	 * @throws SQLException
	 */
	public List<Student> quaryByDept(String college) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select *,count(distinct id) from studentinfo where college = '" + college + "' group by id";
		if (college.equalsIgnoreCase("西北师范大学")) {
			sql = "select *,count(distinct id) from studentinfo group by id";
		}
		//执行
		ResultSet rs = stmt.executeQuery(sql);
		//添加用户信息
		List<Student> stuList = new ArrayList<Student>();
		Student s = null;
		while(rs.next()) {
			s = new Student(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("major"), rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
			stuList.add(s);
		}
		return stuList;
	}
	
}
