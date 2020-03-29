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
 * ����ѯѧ����Ϣ
 * 
 * @author Luor
 * @version 1.0
 */
public class StudentDao {
	
	/**
	 * ������ѧ������
	 * 
	 * @param s
	 * @return ִ�н��
	 * @throws SQLException
	 */
	public boolean addStudent(Student s) throws SQLException {
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "Insert into studentinfo(id,name,sex,college,major,phoneNumber,recordDate,province,city,diagnosed,temperature)"
				+"values(?,?,?,?,?,?,?,?,?,?,?)";
		//Ԥ����
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//����
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
		//ִ��
		try {
			ptmt.execute();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	
	/**
	 * ��ͨ��ѧ�Ų�ѯ��Ϣ
	 * 
	 * @param id
	 * @return ѧ���б�
	 * @throws SQLException
	 */
	public List<Student> quaryById(String id) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select * from studentinfo where id = " + id;
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//����û���Ϣ
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
	 * ��ͨ��ѧ�������ڲ�ѯ����
	 * 
	 * @param id
	 * @param recordDate
	 * @return ѧ��ʵ��
	 * @throws SQLException
	 */
	public Student findStudentInfo(String id, Date recordDate) throws SQLException {
		Student s = null;
		//��ȡ���ݿ�����
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "select * from studentinfo where id = ? and recordDate = ?";
		//Ԥ����
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//����
		ptmt.setString(1, id);
		ptmt.setDate(2, recordDate);
		//ִ��
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			s = new Student(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("major"), rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
		}
		return s;
	}
	
	/**
	 * ��ͨ����ʼ�������ֹ���ڲ�ѯ����
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @param isProvince
	 * @return ѧ����Ϣ�б�
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
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//����û���Ϣ
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
	 * ������ѧԺ��������Ϣ��ѯѧ������
	 * 
	 * @param college
	 * @param date
	 * @return ѧ����Ϣ�б�
	 * @throws SQLException
	 */
	public List<Student> quaryByDeptAndDate(String college, Date date) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select * from studentinfo where college = '" + college + "' and recordDate = '" + date + "'";
		if (college.equalsIgnoreCase("����ʦ����ѧ")) {
			sql = "select * from studentinfo where recordDate = '" + date + "'";
		}
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//����û���Ϣ
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
	 * ������ѧԺ��Ϣ��ѯѧ������
	 * ��ע���˲�ѯ�������޸�MySQL��Ĭ��ģʽ����ȥ��select @@global.sql_mode��ѯ�е�ONLY_FULL_GROUP_BY
	 * ��ע��ʹ��set @@global.sql_mode = ''����
	 * 
	 * @param college
	 * @return ѧ����Ϣ�б�
	 * @throws SQLException
	 */
	public List<Student> quaryByDept(String college) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select *,count(distinct id) from studentinfo where college = '" + college + "' group by id";
		if (college.equalsIgnoreCase("����ʦ����ѧ")) {
			sql = "select *,count(distinct id) from studentinfo group by id";
		}
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//����û���Ϣ
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
