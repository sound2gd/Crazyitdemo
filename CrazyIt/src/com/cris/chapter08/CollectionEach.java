package com.cris.chapter08;

import java.util.Collection;
import java.util.HashSet;

/**
 * 使用lambda表达式遍历集合
 * @author cris
 *
 */
public class CollectionEach {

	public static void main(String[] args) {
		//创建一个集合
		Collection c = new HashSet();
		c.add("轻量级JavaEE");
		c.add("Crazy Java3");
		c.add("EffectiveJava");
		//Java8新增，传入的是一个Consumer函数式接口
		//forEach会调用Consumer中的唯一抽象accept方法
		c.forEach( obj -> System.out.println("迭代集合元素:"+obj));
	}
	
}
