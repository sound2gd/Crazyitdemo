package com.cris.chapter15.f5;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 测试另一种序列化实现机制java.io.Externalizable<br>
 * @author sound2gd
 *
 */
public class PersonEx implements Externalizable{

	private String name;
	private int age;
	//使用Externalizable反序列化没有无参构造器会报错
	public PersonEx() {
	}
	public PersonEx(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "PersonEx [name=" + name + ", age=" + age + "]";
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(new StringBuffer(name).reverse());
		out.writeInt(age);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name = in.readObject().toString();
		this.age= in.readInt();
	}

}
