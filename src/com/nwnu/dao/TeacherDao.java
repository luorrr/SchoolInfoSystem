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
		//����û���Ϣ
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
	
	/**
	 * ��ͨ����ʼ�������ֹ���ڲ�ѯ����
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @param isProvince
	 * @return ��ʦ��Ϣ�б�
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
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//����û���Ϣ
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
	
	/**
	 * ������ѧԺ��������Ϣ��ѯ��ʦ����
	 * 
	 * @param college
	 * @param date
	 * @return ��ʦ��Ϣ�б�
	 * @throws SQLException
	 */
	public List<Teacher> quaryByDeptAndDate(String college, Date date) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select * from teacherinfo where college = '" + college + "' and recordDate = '" + date + "'";
		if (college.equalsIgnoreCase("����ʦ����ѧ")) {
			sql = "select * from teacherinfo where recordDate = '" + date + "'";
		}
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//����û���Ϣ
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
	 * ������ѧԺ��Ϣ��ѯ��ʦ����
	 * ��ע���˲�ѯ�������޸�MySQL��Ĭ��ģʽ����ȥ��select @@global.sql_mode��ѯ�е�ONLY_FULL_GROUP_BY
	 * ��ע��ʹ��set @@global.sql_mode = ''����
	 * 
	 * @param college
	 * @return ��ʦ��Ϣ�б�
	 * @throws SQLException
	 */
	public List<Teacher> quaryByDept(String college) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select *,count(distinct id) from teacherinfo where college = '" + college + "' group by id";
		if (college.equalsIgnoreCase("����ʦ����ѧ")) {
			sql = "select *,count(distinct id) from teacherinfo group by id";
		}
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//����û���Ϣ
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
	
}
