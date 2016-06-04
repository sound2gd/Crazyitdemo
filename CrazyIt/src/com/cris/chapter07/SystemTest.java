package com.cris.chapter07;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * System类demo
 * System类代表了当前Java程序的运行平台。
 * 通过System类可以得到环境变量，系统时间，根据对象地址计算出来的HashCode等等
 * @author cris
 *
 */
public class SystemTest {
	public static void main(String[] args) {
		//获取系统所有的环境变量
		Map<String, String> getenv = System.getenv();
		System.out.println("当前系统环境变量为:");
		for (Map.Entry<String, String> env:getenv.entrySet()){
			System.out.println(env.getKey() + "  --->  "+env.getValue());
		}
		//获取系统指定环境变量的值
		System.out.println(System.getenv("JAVA_HOME"));
		
		//获取到所有的系统属性
		Properties properties = System.getProperties();
		Enumeration<?> propertyNames = properties.propertyNames();
		System.out.println("当前系统的属性为:");
		while (propertyNames.hasMoreElements()){
			String nextElement = (String) propertyNames.nextElement();
			System.out.println(nextElement+" ---> "+properties.getProperty(nextElement));
		}
		
		//获取到基于对象地址得到的HashCode
		String str1=new String("Hello");
		String str2=new String("Hello");
		//由于String类重写了HashCode方法，改成了基于字符串序列得到HashCode,所以以下HashCode相同
		System.out.println(str1.hashCode()+","+str2.hashCode());
		//根据对象地址得到的HashCode不一样
		System.out.println(System.identityHashCode(str1)+","+System.identityHashCode(str2));
		
		String str3="Hello";
		String str4="Hello";
		System.out.println(str3.hashCode()+","+str4.hashCode());
		//引用的同一个常量池的对象，所以相同的HashCode
		System.out.println(System.identityHashCode(str3)+","+System.identityHashCode(str4));
	}
}
