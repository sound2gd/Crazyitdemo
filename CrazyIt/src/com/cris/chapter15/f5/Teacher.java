package com.cris.chapter15.f5;

import java.io.Serializable;

public class Teacher implements Serializable {

	private String name;
	private Person student;

	public Teacher(String name, Person student) {
		super();
		System.out.println("Teacher有参数的构造器");
		this.name = name;
		this.student = student;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", student=" + student + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = student;
	}

}
