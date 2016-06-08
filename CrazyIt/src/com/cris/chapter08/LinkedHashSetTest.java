package com.cris.chapter08;

import java.util.LinkedHashSet;

/**
 * 测试LinkedHashSet的用法<br>
 * LinkedHashSet维护了一个链表<br>
 * 因此遍历LinkedHashSet的时候性能比HashSet要好
 * @author cris
 *
 */
public class LinkedHashSetTest {

	public static void main(String[] args) {
		LinkedHashSet<Object> set =new LinkedHashSet<>();
		set.add("疯狂Java讲义");
		set.add("Spring讲义");
		set.add("Hibernate讲义");
		//输出LinkedHashSet时，会按照对象的添加顺序输出
		System.out.println(set);
	}
}
