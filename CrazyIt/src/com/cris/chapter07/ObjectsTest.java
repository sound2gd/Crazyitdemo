package com.cris.chapter07;

import java.util.Objects;

/**
 * 测试Objects类<br/>
 * Java的习惯是给工具类加上一个s，如Arrays
 * @author cris
 *
 */
public class ObjectsTest {
	static Object obj;
	public static void main(String[] args) {
		//输出obj的值,它是null,所以hashcode是０
		System.out.println(Objects.hashCode(obj));
		//输出一个null对象的toString
		System.out.println(Objects.toString(obj));
		//要求Obj不为null,为null则抛异常
		System.out.println(Objects.requireNonNull(obj,"参数不能是null!"));
	}
	
}
