package com.cris.chapter13;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * 测试使用PrepareState来执行SQL<br>
 * PrepareStatement可以使用占位符,避免SQL注入
 * 创建PreapareStatement可以传入参数，达到创建动态可更新的结果集
 * 
 * @author sound2gd
 *
 */
public class PreparedStatementTest2 {

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
						+ "select id,name from test_a",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = statement.executeQuery();) {
			//滚动结果集到最后	
			rs.last();
			int rowCount = rs.getRow();
			
			for (int i = rowCount; i > 0; i--) {
				rs.absolute(i);
				//更新
				rs.updateString(2, "i am your dad");
				//提交修改
				rs.updateRow();
			}
			
		}
	}

	public static void main(String[] args) throws Exception {
		PreparedStatementTest2 test = new PreparedStatementTest2();
		test.init("src/com/cris/chapter13/resource/mysql.properties");
		test.executeInsert();
	}

}
