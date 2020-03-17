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
 * ����ѯ��ʦ��Ϣ
 * 
 * @author Luor
 * @version 1.0
 */
public class TeacherDao {
	
	/**
	 * �������ʦ����
	 * 
	 * @param t
	 * @return ִ�н��
	 * @throws SQLException
	 */
	public boolean addTeacher(Teacher t) throws SQLException {
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "Insert into teacherinfo(id,name,sex,college,phoneNumber,recordDate,province,city,diagnosed,temperature)"
				+"values(?,?,?,?,?,?,?,?,?,?)";
		//Ԥ����
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//����
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
		//ִ��
		try {
			ptmt.execute();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	
	/**
	 * ��ͨ�����Ų�ѯ��Ϣ
	 * 
	 * @param id
	 * @return ��ʦ�б�
	 * @throws SQLException
	 */
	public List<Teacher> quaryById(String id) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select * from teacherinfo where id = " + id;
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//�����û���Ϣ
		List<Teacher> tea = new ArrayList<Teacher>();
		Teacher t = null;
		while(rs.next()) {
			t = new Teacher(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
			tea.add(t);
		}
		return tea;
	}
	
	/**
	 * ��ͨ�����������ڲ�ѯ����
	 * 
	 * @param id
	 * @param recordDate
	 * @return ��ʦʵ��
	 * @throws SQLException
	 */
	public Teacher findTeacherInfo(String id, Date recordDate) throws SQLException {
		Teacher t = null;
		//��ȡ���ݿ�����
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "select * from teacherinfo where id = ? and recordDate = ?";
		//Ԥ����
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//����
		ptmt.setString(1, id);
		ptmt.setDate(2, recordDate);
		//ִ��
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			t = new Teacher(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
		}
		return t;
	}
}