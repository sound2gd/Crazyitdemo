package com.cris.chapter08;

import java.util.TreeSet;

/**
 * 测试TreeSet的用法<br>
 * 元素放入TreeSet会默认按照自然排序法升序排列<br>
 * 
 * @author cris
 *
 */
public class TreeSetTest {

	public static void main(String[] args) {
		TreeSet<Object> set =new TreeSet<>();
		set.add(1);
		set.add(-1);
		set.add(11);
		set.add(2);
		set.add(453);
		//TreeSet不允许插入null,下面一行会报空指针异常
//		set.add(null);
		
		//输出升序排列的元素
		System.out.println(set);
		
		//输出集合里的第一个元素
		System.out.println("集合里的第一个元素:"+set.first());
		
		//集合里的最后一个元素
		System.out.println("集合里的最后一个元素:"+set.last());
		
		//集合里大于４的元素（最近的一个）
		System.out.println("集合里大于４的元素:"+set.higher(4));
		
		//集合里小于4的元素（最近的一个）
		System.out.println("集合里小于4的元素:"+set.lower(4));
		
		//大于4的子集
		System.out.println(set.tailSet(4));
		
		//小于４的子集
		System.out.println(set.headSet(4));
		
		//大于0小于4的子集
		System.out.println(set.subSet(0, 4));
	}
}
