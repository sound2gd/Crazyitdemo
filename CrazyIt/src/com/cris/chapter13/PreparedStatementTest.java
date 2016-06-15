package com.cris.chapter13;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

/**
 * 测试使用PrepareState来执行SQL<br>
 * PrepareStatement可以使用占位符,避免SQL注入
 * 
 * @author sound2gd
 *
 */
public class PreparedStatementTest {

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

	public void executeInsert() throws Exception {
		// 加载驱动
		Class.forName(driver);
		try (
				// 以下类都实现了AutoClosable接口,可以使用try-with-resource来关闭
				Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement statement=connection.prepareStatement(""
						+ "insert into test_create(name) values(?)");) {
			//使用预编译的语句来处理可以使用占位符，避免被SQL注入
			statement.setString(1, "哈哈");
			System.out.println("受影响的行数:" + statement.executeUpdate());
		}
	}

	public static void main(String[] args) throws Exception {
		PreparedStatementTest test = new PreparedStatementTest();
		test.init("src/com/cris/chapter13/resource/mysql.properties");
		test.executeInsert();
	}

}
