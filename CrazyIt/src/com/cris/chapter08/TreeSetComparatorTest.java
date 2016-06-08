package com.cris.chapter08;

import java.util.TreeSet;

/**
 * 测试TreeSet，实现自定义排序<br>
 * TreeSet中的排序默认是自然排序<br>
 * 用户可以自定义排序规则，以使用自己的排序
 * 
 * @author cris
 *
 */
public class TreeSetComparatorTest {

	public static void main(String[] args) {
		//使用lambda表达式自定义排序规则
		TreeSet<M> set  = new TreeSet<>( (obj1,obj2) ->{
			// 根据age的大小倒序排
			return obj1.age > obj2.age ? -1 : obj1.age == obj2.age ? 0 : 1;
		});
		set.add(new M(3));
		set.add(new M(234));
		set.add(new M(-13));
		set.add(new M(0));
		set.add(new M(2));
		set.add(new M(3));
		
		System.out.println(set);
	}
}

class M{

	int age;
	public M(int age) {
		super();
		this.age = age;
	}

	@Override
	public String toString() {
		return "M [age=" + age + "]";
	}
}