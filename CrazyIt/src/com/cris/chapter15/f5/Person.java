package com.cris.chapter15.f5;

import java.io.Serializable;

/**
 * 测试的用来序列化的实体类
 * 
 * @author sound2gd
 *
 */
public class Person implements Serializable {

	private String name;
	private transient int age;

	public Person(String name, int age) {
		super();
		System.out.println("Person有参数的构造器");
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
//=======================================================================
	//自定义序列化，上面的transient不起作用
	
	private void writeObject(java.io.ObjectOutputStream out) throws Exception {
		// 自定义序列化
		// 将name的值反转后写入二进制流
		out.writeObject(new StringBuffer(name).reverse());
		out.writeInt(age);
	}
	
	private void readObject(java.io.ObjectInputStream in) throws Exception {
		this.name=in.readObject().toString();
		this.age = in.readInt();
	}
	
	
}
