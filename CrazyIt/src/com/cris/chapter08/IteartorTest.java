package com.cris.chapter08;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 测试迭代器Iterator
 * @author cris
 *
 */
public class IteartorTest {

	public static void main(String[] args) {
		Collection c = new HashSet();
		c.add("轻量级JavaEE");
		c.add("Crazy Java3");
		c.add("EffectiveJava");
		
		//使用迭代器遍历,Java集合都实现了Itreator接口
		Iterator it = c.iterator();
		while (it.hasNext()){
			Object next = it.next();
			System.out.println("元素:"+next);
			
			if(next.equals("Crazy Java3")){
				it.remove();
			}
		}
		System.out.println(c);
	}
	
}
