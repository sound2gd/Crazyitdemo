package com.cris.chapter08;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试Java8中为List新增的方法<br>
 * Java8为List新增了两个方法:
 * <li>sort(Comparable c)</li>
 * <li>replaceAll(UnaryOperator uo)</li>
 * @author cris
 *
 */
public class ListJava8Test {

	public static void main(String[] args) {
		//新建一个集合
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(22);
		list.add(3);
		list.add(5);
		list.add(11);
		list.add(0);
		list.add(-11);
		list.add(-23);
		
		System.out.println("排序前:"+list);
		//通过lambda表达式指定规则，此处为示例升序
		list.sort((o1,o2) -> o1>o2?1:o1==o2?0:-1);
		System.out.println("排序后:"+list);
		
		//使用reaplaceAll来返回一个新的集合
		list.replaceAll(obj -> obj*2+1);
		System.out.println("replaceAll:"+list);
		
		
	}

}
