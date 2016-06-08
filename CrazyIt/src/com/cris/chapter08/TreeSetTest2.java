package com.cris.chapter08;

import java.util.Date;
import java.util.TreeSet;

/**
 * 往TreeSet里添加元素必须保证其实现了Comparable接口<br>
 * 不然会导致元素无法排序，自然往外抛出异常<br>
 * 如果希望TreeSet能正常运作，TreeSet只能添加同一种类型的对象
 * @author cris
 *
 */
public class TreeSetTest2 {

	public static void main(String[] args) {
		TreeSet<Object> set = new TreeSet<>();
//		set.add(new Date());
		set.add(new Err());
		set.add(new Err());
		
		/**
		 * 输出如下错误:
		 * Exception in thread "main" java.lang.ClassCastException:
		 * com.cris.chapter08.Err cannot be cast to java.lang.Comparable at
		 * java.util.TreeMap.compare(TreeMap.java:1290) at
		 * java.util.TreeMap.put(TreeMap.java:538) at
		 * java.util.TreeSet.add(TreeSet.java:255) at
		 * com.cris.chapter08.TreeSetTest2.main(TreeSetTest2.java:15)
		 */
	}

}

class Err {
}
