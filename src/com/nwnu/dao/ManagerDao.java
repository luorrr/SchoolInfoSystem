package com.nwnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nwnu.pojo.Manager;

/**
 * Filename: ManageDao.java
 * 
 * ����ѯ����Ա��Ϣ
 * 
 * @author Luor
 * @version 1.0
 */
public class ManagerDao {
	
	/**
	 * ������Ա��¼��Ϣ��ѯ
	 * 
	 * @param id
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public Manager findManager(String id, String password) throws SQLException {
		Manager m = null;
		// ��ȡ���ݿ�����
		Connection conn = DbUtil.getConnection();
		// SQL
		String sql = "select * from manager where id = ? and password = ?";
		// Ԥ����
		PreparedStatement ptmt = conn.prepareStatement(sql);
		// ����
		ptmt.setString(1, id);
		ptmt.setString(2, password);
		// ִ��
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			m = new Manager(rs.getString("id"), rs.getString("name"), rs.getString("password"), rs.getString("dept"));
		}
		return m;
	}
}
