package com.cris.chapter13;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

/**
 * 使用execute可以执行任何SQL语句<br>
 * ，但是执行的时候比较麻烦，通常没有必要使用execute执行SQL<br>
 * 改用executeQuery来执行更适合
 * 
 * @author sound2gd
 *
 */
public class ExecuteTest {

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

	public void executeSQL(String sql) throws Exception {
		// 加载驱动
		Class.forName(driver);
		try (
				// 以下类都实现了AutoClosable接口,可以使用try-with-resource来关闭
				Connection connection = DriverManager.getConnection(url, user, pass);
				Statement statement = connection.createStatement();) {

			// 使用execute来执行SQL
			boolean execute = statement.execute(sql);
			// 如果有结果集
			if (execute) {
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData metaData = resultSet.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					System.out.print(metaData.getColumnName(i+1)+"\t");
				}
				System.out.println();
				System.out.println("-------------------------------");
				while(resultSet.next()){
					for (int i = 0; i < columnCount; i++) {
						System.out.print(resultSet.getString(i+1)+"\t");
					}
					System.out.println();
				}
			}else{
				//返回受影响的行数
				System.out.println("受影响的行数:"+statement.getUpdateCount());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ExecuteTest exec = new ExecuteTest();
		exec.init("src/com/cris/chapter13/resource/mysql.properties");
//		// 执行DDL
//		exec.executeSQL("drop table if exists test_create");
		// 测试执行DDL
//		exec.executeSQL(
//				"create table test_create(" + "id int auto_increment primary key," + "name varchar(255) unique);");
		// 测试执行DML
		exec.executeSQL("insert into test_create(name) values('test_name3')");
		// 测试执行查询
		exec.executeSQL("select * from test_create");
		// 以上测试执行成功
	}

}
