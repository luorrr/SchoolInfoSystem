package com.nwnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nwnu.pojo.Manager;

/**
 * Filename: ManageDao.java
 * 
 * ·查询管理员信息
 * 
 * @author Luor
 * @version 1.0
 */
public class ManagerDao {
	
	/**
	 * ·管理员登录信息查询
	 * 
	 * @param id
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public Manager findManager(String id, String password) throws SQLException {
		Manager m = null;
		// 获取数据库连接
		Connection conn = DbUtil.getConnection();
		// SQL
		String sql = "select * from manager where id = ? and password = ?";
		// 预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		// 传参
		ptmt.setString(1, id);
		ptmt.setString(2, password);
		// 执行
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			m = new Manager(rs.getString("id"), rs.getString("name"), rs.getString("password"), rs.getString("dept"));
		}
		return m;
	}
}
