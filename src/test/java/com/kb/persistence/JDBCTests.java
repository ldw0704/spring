package com.kb.persistence;

import java.sql.*;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	private String url ="jdbc:mysql://localhost:3306/smart?characterEncoding=utf-8&serverTimezone=Asia/Seoul";
	private String user ="root";
	private String password ="smart";
	//junit테스트
	@Test
	public void testConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc,Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			log.info(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
