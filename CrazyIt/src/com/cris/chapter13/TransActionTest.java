package com.cris.chapter13;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * 测试Jdbc的事务<br>
 * 事务的原则是ACID，执行DDL和DCL都会导致事务立刻提交
 * 
 * @author sound2gd
 *
 */
public class TransActionTest {

	private String driver;
	private String url;
	private String user;
	private String pass;

	// 从配置文件里加载属性
	private void init(String filePath) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(filePath));
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		pass = prop.getProperty("pass");
	}
	
	public void executeJdbc(String... sqls) throws Exception {
		// 加载驱动
		Class.forName(driver);
		try (
				// 以下类都实现了AutoClosable接口,可以使用try-with-resource来关闭
				Connection connection = DriverManager.getConnection(url, user, pass);) {
			//关闭自动提交
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			for(String sql:sqls){
				statement.executeUpdate(sql);
			}
			connection.commit();
		}
	}
	
	public static void main(String[] args) throws Exception {
		TransActionTest test = new TransActionTest();
		test.init("src/com/cris/chapter13/resource/mysql.properties");
		test.executeJdbc(
				"insert into student_table values(null,'aaa',1)",
				"insert into student_table values(null,'bbb',1)",
				"insert into student_table values(null,'ccc',1)",
				//下面这行会违反外键约束
				//导致事务非正常结束，然后回滚事务
				"insert into student_table values(null,'ddd',5)"
				);
	}
	
}
