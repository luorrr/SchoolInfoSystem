package com.nwnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Filename: DbUtil.java
 * 
 * ·连接数据库
 * 
 * @author Luor
 * @version 1.0
 */
public class DbUtil {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/schooldb?serverTimezone=GMT%2B8";
	public static final String USER = "luor";
	public static final String PASSWORD = "990215lr";
	
	private static Connection conn = null;
	
	static {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ·返回数据库连接
	 * @return conn
	 */
	public static Connection getConnection() {
		return conn;
	}
}
