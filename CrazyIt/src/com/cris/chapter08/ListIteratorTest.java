package com.cris.chapter08;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 测试ListIterator<br>
 * ListIterator用于遍历List,除了普通Iterator的用法之外，<br>
 * 它额外提供了hasPrevious,Previous,add方法
 * 
 * @author cris
 *
 */
public class ListIteratorTest {
	public static void main(String[] args) {
		// 新建一个集合
		List<String> list = new ArrayList<>();
		list.add("fkJava1");
		list.add("fkJava2");
		list.add("fkJava3");
		list.add("fkJava4");
		list.add("fkJava5");
		list.add("fkJava6");
		list.add("fkJava7");
		
		ListIterator<String> it = list.listIterator();
		while(it.hasNext()){
			//正向迭代
			System.out.println(it.next());
		}
		System.out.println("--------------------分隔符----------------------------");
		while(it.hasPrevious()){
			//反向迭代
			System.out.println(it.previous());
		}
		
	}
}
