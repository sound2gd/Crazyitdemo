package com.cris.chapter14;

/**
 * 一个普通的Java类，使用这三个注解来修饰
 * 
 * @author cris
 *
 */
@Persistence(table = "test_person")
public class Person {

	@Id(column = "id", type = "int", generator = "identity")
	private int id;
	@Property(column = "name", type = "String")
	private String name;
	@Property(column = "age", type = "int")
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
