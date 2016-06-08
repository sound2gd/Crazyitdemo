package com.cris.chapter08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 测试Properties<br>
 * Property是Hashtable的子类,专门用于读写配置文件(键值对的形式)
 * 
 * @author cris
 *
 */
public class PropertiesTest {

	public static void main(String[] args) {
		Properties props = new Properties();
		// 往Properties中添加属性使用setProperty方法
		props.setProperty("AJAX", "疯狂AJAX讲义");
		props.setProperty("JAVA", "疯狂JAVA讲义");
		props.setProperty("IOS", "疯狂IOS讲义");
		props.setProperty("ANDROID", "疯狂ANDROID讲义");
		props.setProperty("JavaScript", "疯狂JavaScript讲义");

		System.out.println(props);
		// 将Properties文件的键值对保存到a.ini文件中,如果文件不存在，将会创建该文件
		try {
			// 往流中写数据的方法是C++方法
			props.store(new FileOutputStream("src/com/cris/chapter08/a.ini"), "这是评论");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Properties p2 = new Properties();
		try {
			// 通过load方法可以从文件中加载配置文件
			p2.load(new FileInputStream("src/com/cris/chapter08/a.ini"));
			System.out.println(p2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
