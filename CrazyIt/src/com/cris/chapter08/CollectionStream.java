package com.cris.chapter08;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Collection都可以转化成流<br>
 * 测试流式编程
 * @author cris
 *
 */
public class CollectionStream {
	public static void main(String[] args) {
		
		Collection c = new HashSet();
		c.add("轻量级JavaEE");
		c.add("Crazy Java3");
		c.add("EffectiveJava");
		
		
		//统计包含java的
		System.out.println( c.stream().filter(ele -> ((String)ele).contains("Java")).count());
		//统计长度大于３的
		System.out.println( c.stream().filter(ele -> ((String)ele).length()>0).count());
		
		//集合转换成Stream
		Stream stream = c.stream();
		stream.mapToInt(ele -> ((String)ele).length())
			//调用forEach方法遍历每一个元素
			.forEach(obj -> System.out.println(obj));
	}
}
