package com.cris.chapter08;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 测试iterator新增的forEachRemaining方法<br>
 * 测试Java8新增的removeIf(Predicate filter)
 * @author cris
 *
 */
public class IteratorForEach {
	public static void main(String[] args) {
		Collection c = new HashSet();
		c.add("轻量级JavaEE");
		c.add("Crazy Java3");
		c.add("EffectiveJava");
		
		c.removeIf( obj -> obj.equals("Crazy Java3"));
		
		Iterator iterator = c.iterator();
		iterator.forEachRemaining( obj -> System.out.println("元素是:"+obj));
	}
}
