package com.nwnu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nwnu.pojo.Teacher;;

/**
 * Filename: TeacherDao.java
 * 
 * ·查询教师信息
 * 
 * @author Luor
 * @version 1.0
 */
public class TeacherDao {
	
	/**
	 * ·插入教师数据
	 * 
	 * @param t
	 * @return 执行结果
	 * @throws SQLException
	 */
	public boolean addTeacher(Teacher t) throws SQLException {
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "Insert into teacherinfo(id,name,sex,college,phoneNumber,recordDate,province,city,diagnosed,temperature)"
				+"values(?,?,?,?,?,?,?,?,?,?)";
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//传参
		ptmt.setString(1, t.getId());
		ptmt.setString(2, t.getName());
		ptmt.setString(3, t.getSex());
		ptmt.setString(4, t.getCollege());
		ptmt.setString(5, t.getPhoneNumber());
		ptmt.setDate(6, t.getRecordDate());
		ptmt.setString(7, t.getProvince());
		ptmt.setString(8, t.getCity());
		ptmt.setString(9, t.getDiagnosed());
		ptmt.setBigDecimal(10, t.getTemperature());
		//执行
		try {
			ptmt.execute();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	
	/**
	 * ·通过工号查询信息
	 * 
	 * @param id
	 * @return 教师列表
	 * @throws SQLException
	 */
	public List<Teacher> quaryById(String id) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select * from teacherinfo where id = " + id;
		//执行
		ResultSet rs = stmt.executeQuery(sql);
		//添加用户信息
		List<Teacher> teaList = new ArrayList<Teacher>();
		Teacher t = null;
		while(rs.next()) {
			t = new Teacher(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
			teaList.add(t);
		}
		return teaList;
	}
	
	/**
	 * ·通过工号与日期查询数据
	 * 
	 * @param id
	 * @param recordDate
	 * @return 教师实体
	 * @throws SQLException
	 */
	public Teacher findTeacherInfo(String id, Date recordDate) throws SQLException {
		Teacher t = null;
		//获取数据库连接
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "select * from teacherinfo where id = ? and recordDate = ?";
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//传参
		ptmt.setString(1, id);
		ptmt.setDate(2, recordDate);
		//执行
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			t = new Teacher(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
		}
		return t;
	}
	
	/**
	 * ·通过起始日期查询数据
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @param isProvince
	 * @return
	 * @throws SQLException
	 */
	public List<Teacher> quaryByDate(Date dateBegin, Date dateEnd, boolean isProvince) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "";
		if (isProvince) {
			sql = "select count(*) count,province from teacherinfo where recordDate between '" + dateBegin + "' and '" + 
					dateEnd + "' and diagnosed = '1' Group By province;";
		} else {
			sql = "select count(*) count,recordDate from teacherinfo where recordDate between '" + dateBegin + "' and '" + 
					dateEnd + "' and diagnosed = '1' Group By recordDate;";
		}
		
		//执行
		ResultSet rs = stmt.executeQuery(sql);
		//添加用户信息
		List<Teacher> teaList = new ArrayList<Teacher>();
		Teacher t = null;
		while(rs.next()) {
			if (isProvince) {
				t = new Teacher(rs.getInt("count"), null, rs.getString("province"));
			} else {
				t = new Teacher(rs.getInt("count"), rs.getDate("recordDate"), null);
			}
			teaList.add(t);
		}
		return teaList;
	}
	
}
