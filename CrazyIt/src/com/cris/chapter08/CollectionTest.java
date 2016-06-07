package com.cris.chapter08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 测试Collection接口的方法<br>
 * Collection和Map是Java集合最顶层的两个接口
 * @author cris
 *
 */
public class CollectionTest {

	public static void main(String[] args) {
		Collection c =  new ArrayList();
		//add方法
		c.add("孙悟空");
		c.add(1);
		//size方法
		System.out.println("Collection的大小:"+c.size());
		c.add("猪八戒");
		//remove 方法
		c.remove("孙悟空");
		System.out.println(c);
		//containsAll方法
		Collection c2 = new HashSet<>();
		c2.add("猪八戒");
		System.out.println(c.containsAll(c2));
		System.out.println(c);
		//retainsAll 交集
		c.retainAll(c2);
		System.out.println(c);
		//clear 删除所有元素
		c.clear();
	}
	
}
