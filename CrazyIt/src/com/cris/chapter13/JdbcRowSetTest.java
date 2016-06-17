package com.cris.chapter13;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/**
 * JdbcRowSet会保持和数据库的链接<br>
 * 可以实时的更新数据库
 * 
 * @author sound2gd
 *
 */
public class JdbcRowSetTest {

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

	public void executeJdbc(String sql) throws Exception {
		// 加载驱动
		Class.forName(driver);
		try (
				// 以下类都实现了AutoClosable接口,可以使用try-with-resource来关闭
				Connection connection = DriverManager.getConnection(url, user, pass);) {

			// 使用RowSetProvider创建RowSetFactory
			RowSetFactory newFactory = RowSetProvider.newFactory();
			// 使用RowSetFactory创建默认的JdbcRowSet实例
			JdbcRowSet jdbcRs = newFactory.createJdbcRowSet();

			jdbcRs.setUrl(url);
			jdbcRs.setUsername(user);
			jdbcRs.setPassword(pass);
			jdbcRs.setCommand(sql);

			jdbcRs.execute();
			jdbcRs.afterLast();
			// 向前滚动结果集
			while (jdbcRs.previous()) {
				System.out.println(jdbcRs.getString(1) + "\t" + jdbcRs.getString(2) + "\t" + jdbcRs.getString(3));
				if (jdbcRs.getInt("student_id") == 3) {
					jdbcRs.updateString("student_name", "sun wu kong");
					jdbcRs.updateRow();
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		JdbcRowSetTest test = new JdbcRowSetTest();
		test.init("src/com/cris/chapter13/resource/mysql.properties");
		test.executeJdbc("select * from student_table");
	}
}
