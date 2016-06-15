package com.cris.chapter13;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * 测试使用Statement的executeUpdate方法<br>
 * 来执行DDL,DML
 * 
 * @author sound2gd
 *
 */
public class ExecuteSQL {

	private String driver;
	private String url;
	private String user;
	private String pass;
	
	//从配置文件里加载属性
	private void init(String filePath) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(filePath));
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		pass = prop.getProperty("pass");
	}

	public void executeSQL(String sql) throws Exception {
		// 加载驱动
		Class.forName(driver);
		try (
				// 以下类都实现了AutoClosable接口,可以使用try-with-resource来关闭
				Connection connection = DriverManager.getConnection(url, user, pass);
				Statement statement = connection.createStatement();) {
			// 使用executeUpdate来执行DDL
			System.out.println("受影响的行数:" + statement.executeUpdate(sql));
			// 执行DDL时，executeUpdate返回0
			// 执行DML时，返回实际受影响的行数
		}
	}

	public static void main(String[] args) throws Exception {
		ExecuteSQL exec = new ExecuteSQL();
		exec.init("src/com/cris/chapter13/resource/mysql.properties");
		//执行DDL
		exec.executeSQL("drop table if exists test_create");
		//测试执行DDL
		exec.executeSQL(
				"create table test_create(" + "id int auto_increment primary key," + "name varchar(255) unique);");
		//测试执行DML
		exec.executeSQL("insert into test_create(name) values('test_name')");
		// 以上测试执行成功
	}

}
