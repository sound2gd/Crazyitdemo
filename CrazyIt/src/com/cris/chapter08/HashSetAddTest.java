package com.cris.chapter08;

import java.util.HashSet;

/**
 * HashSet的插入规则测试<br>
 * HashSet判断两个元素是否重复的规则是：
 * <li>equals方法返回true</li>
 * <li>hashCode方法返回值相等</li>
 * 
 * @author cris
 *
 */
public class HashSetAddTest {
	
	public static void main(String[] args) {
		HashSet<Object> set = new HashSet<>();
		set.add(new A());
		set.add(new A());
		set.add(new B());
		set.add(new B());
		set.add(new C());
		set.add(new C());
		//可以看出，虽然A的equals都是true,但是保存了俩对象
		//B的hashCode相等，还是俩对象
		//C只有一个
		
		//[B@1,B@1,C@2,A@659e0bfd,A@2a139a55]
		System.out.println(set);
	}
	
}

/**
 * A类重写equals方法，总是返回true
 * @author cris
 *
 */
class A{

	@Override
	public boolean equals(Object obj) {
		return true;
	}
	
}

/**
 * B类重写HashCode方法，总是返回１
 * @author cris
 *
 */
class B{

	@Override
	public int hashCode() {
		return 1;
	}
	
}
/**
 * C类重写HashCode和equals方法
 * @author cris
 *
 */
class C{

	@Override
	public int hashCode() {
		return 2;
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}
	
}
