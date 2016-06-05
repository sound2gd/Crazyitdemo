package com.cris.chapter06;
/**
 * 测试Java8中默认方法的继承性
 * @author cris
 *
 */
public interface InterfaceExtendsTest extends BaseInterface{
	//证明:接口的默认方法是可以继承的
	public static void main(String[] args) {
		new InterfaceExtendsTest(){
		}.testI();;
	}
	
}

interface BaseInterface{
	default void testI(){
		System.out.println("这是父接口的默认方法");
	}
	static void testP(){
		System.out.println("这是父接口的类方法");
	}
}
