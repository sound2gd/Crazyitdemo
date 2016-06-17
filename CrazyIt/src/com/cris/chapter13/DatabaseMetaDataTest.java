package com.cris.chapter13;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用DatabaseMetaData来分析数据库信息
 * 
 * @author sound2gd
 *
 */
public class DatabaseMetaDataTest {

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

	public void info() throws Exception {
		// 加载驱动
		Class.forName(driver);
		try (
				// 以下类都实现了AutoClosable接口,可以使用try-with-resource来关闭
				Connection connection = DriverManager.getConnection(url, user, pass);) {
			//获取DatabaseMetaData对象
			DatabaseMetaData data = connection.getMetaData();
			//获取Mysql支持的所有表类型
			ResultSet rs = data.getTableTypes();
			System.out.println("--Mysql支持的表信息类型--");
			//打印
			printResultSet(rs);
			
			//获取student_table的主键
			rs=data.getPrimaryKeys(null, null, "student_table");
			System.out.println("--student_table的主键--");
			printResultSet(rs);
			
			//获取当前数据库的全部存储过程
			rs = data.getProcedures(null, null, "%");
			System.out.println("--存储过程----------------");
			printResultSet(rs);
			
			//获取student_table和teacher_table的外键列
			rs = data.getCrossReference(null, null, "teacher_table", null, null, "student_table");
			System.out.println("--外检约束----------------");
			printResultSet(rs);
			
			//获取student_table表的全部数据列
			rs = data.getColumns(null, null, "student_table", "%");
			System.out.println("--student_tabele的全部数据列----------------");
			printResultSet(rs);
		}
	}
	
	public void printResultSet(ResultSet rs) throws Exception{
		ResultSetMetaData rsmd = rs.getMetaData();
		//打印ResultSet的所有列标题
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnName(i+1)+"\t");
		}
		System.out.println();
		System.out.println("--------------------------------");
		//打印ResultSet里所有的标题
		while(rs.next()){
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				System.out.print(rs.getString(i+1)+"\t");
			}
			System.out.print("\n");
		}
		rs.close();
	}
	
	public static void main(String[] args) throws Exception {
		DatabaseMetaDataTest test = new DatabaseMetaDataTest();
		test.init("src/com/cris/chapter13/resource/mysql.properties");
		test.info();
	}

}
