package com.cris.chapter06;
/**
 * 测试final变量的宏替换
 * @author cris
 *
 */
public class FinalReplaceTest {

	public static void main(String[] args) {
		final String str1 = "Hello World!";
		//str2会被直接替换成Hello World!直接量
		final String str2 = "Hello "+"World!";
		
		System.out.println(str1 == str2);
		String s1 = "Hello ";
		String s2 = "World!";
		//如果s1和s2声明为final，则返回true
		final String str3 = s1+s2;
		//由于编译的时候无法确定str3的值，所以无法让str3执行字符串值指向的对象
		System.out.println(str1==str3);
	}
	
}
