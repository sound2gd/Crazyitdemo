package com.cris.chapter08;

import java.util.List;
import java.util.ArrayList;

/**
 * 测试List的基本用法<br>
 * List代表一个有序，可重复的集合，集合中每个元素都有其对应的索引<br>
 * List集合判断两个对象是否相等的标准是equals方法是否返回true
 * @author cris
 *
 */
public class ListTest {
	
	public static void main(String[] args) {
		List<Object> list =new ArrayList<>();
		list.add(1);
		list.add("fkJava1");
		list.add("fkJava2");
		list.add("fkJava3");
		list.add("fkJava4");
		
		//在指定的索引下添加元素
		list.add(1,"这是添加的元素");
		System.out.println(list);
		//普通for循环遍历list
		for (int i = 0; i < list.size(); i++) {
			//通过get方法可以取出指定索引的元素
			System.out.println(list.get(i));
		}
		
		//指定索引删除元素
		list.remove(2);
		System.out.println(list);
		
		//返回指定元素的位置
		System.out.println(list.indexOf("fkJava"));//不存在所以是-1
		//设置指定位置的元素为指定值
		list.set(0, "哈哈");
		System.out.println(list);
		
		//将list元素的第2个（包括）到第4个(不包括)截取成新的集合
		System.out.println(list.subList(1, 3));
		
		//只要equals返回true就是相等
		list.remove(new String("fkJava3"));
		System.out.println(list);
		
	}
	
}
