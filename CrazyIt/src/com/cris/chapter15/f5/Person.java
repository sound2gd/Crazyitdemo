package com.cris.chapter15.f5;

import java.io.Serializable;

/**
 * 测试的用来序列化的实体类
 * 
 * @author sound2gd
 *
 */
public class Person implements Serializable{

	private String name;
	private int age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
