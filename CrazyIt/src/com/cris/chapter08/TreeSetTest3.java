package com.cris.chapter08;

import java.util.TreeSet;

/**
 * 测试TreeSet的元素添加标准<br>
 * TreeSet元素的元素添加的唯一标准是:
 * <li>两个对象通过compareTo方法返回0</li>
 * @author cris
 *
 */
public class TreeSetTest3 {

	public static void main(String[] args) {
		TreeSet<Person> set =new TreeSet<>();
		Person person =new Person(19);
		set.add(person);
		set.add(person);
		set.add(person);
		set.add(person);
		set.add(person);
		set.add(person);

		//虽然每次add同一个对象，但是重写了compareTo方法
		//所以可以重复保存同一个对象
		System.out.println(set);
	}
	
}

class Person implements Comparable<Person>{
	int age;

	public Person(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}

	@Override
	public int compareTo(Person o) {
		return 1;
	}
}
