package com.cris.chapter13;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 测试开源连接池<br>
 * 数据库连接池在应用程序启动时，系统主动去建立足够的数据库连接<br>
 * 并将这些连接组成一个连接池，下次请求数据库连接时，无需重新打开连接<br>
 * 而是从连接池中拿出一个已有连接
 * 
 * @author sound2gd
 *
 */
public class ConnectionPoolTest {

	public static void main(String[] args) throws Exception{
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/educ?characterEncoding=utf8");
		ds.setUser("root");
		ds.setPassword("wang");
		
		//设置连接池的最大连接数
		ds.setMaxPoolSize(200);
		//设置连接池的最小连接数
		ds.setMinPoolSize(100);
		ds.setInitialPoolSize(70);
		//设置连接池的缓存Statement最大数
		ds.setMaxStatements(180);
		
		Connection conn = ds.getConnection();
		System.out.println("获取连接成功"+conn);
	}
	
}
