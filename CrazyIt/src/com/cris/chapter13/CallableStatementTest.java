package com.cris.chapter13;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Properties;

/**
 * 测试使用CallableStatement来调用存储过程<br>
 * PrepareStatement可以使用占位符,避免SQL注入
 * 
 * @author sound2gd
 *
 */
public class CallableStatementTest {

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
				CallableStatement statement=connection.prepareCall(""
						+ "{call add_pro(?,?,?)}");) {
			//使用预编译的语句来处理可以使用占位符，避免被SQL注入
			statement.setInt(1, 2103_998);
			statement.setInt(2, 123);
			
			//注册结果
			statement.registerOutParameter(3, Types.INTEGER);
			statement.execute();
			
			//获取并输出结果
			System.out.println("执行的结果是:"+statement.getInt(3));
		}
	}

	public static void main(String[] args) throws Exception {
		CallableStatementTest test = new CallableStatementTest();
		test.init("src/com/cris/chapter13/resource/mysql.properties");
		test.executeInsert();
	}

}
