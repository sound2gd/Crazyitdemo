package com.cris.chapter13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * 测试JDBC编程步骤连接MySQL
 * 
 * @author cris
 *
 */
public class ConnMySQL {

	public static void main(String[] args) throws Exception {
		// 1，加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 2，获得数据库连接
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/educ", "root", "wang");
		// 3，获得Statement来执行SQL
		Statement statement = connection.createStatement();
		// 4，执行SQL
		ResultSet result = statement.executeQuery("select * from test_a");
		// 5，操作结果集
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			System.out.print(metaData.getColumnName(i+1)+"\t");
		}
		System.out.print("\n");
		while (result.next()){
			for (int i = 0; i < columnCount; i++) {
				System.out.print(result.getString(i+1)+"\t");
			}
//			System.out.print("\n");
			System.out.println();
		}
		// 6，释放数据库资源
		connection.close();
		statement.close();
		result.close();
	}

}
