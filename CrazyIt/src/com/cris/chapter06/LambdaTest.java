package com.cris.chapter06;

import java.util.Arrays;

import javax.swing.JFrame;

public class LambdaTest {

	public static void main(String[] args) {
		//引用类方法
		Converface conv = from -> Integer.valueOf(from);
		//通过　类名::类方法　来定义lambda
		conv = Integer::valueOf;
		System.out.println(conv.valueOf("123123"));
		//引用特定对象的实例方法
		conv = from -> "HelloWorld".indexOf(from);
		//通过　特定对象::实例方法
		conv = "HelloWorld"::indexOf;
		System.out.println(conv.valueOf("e"));
		
		//引用某类对象的实例方法
		Converter converter = (text,from,to) -> text.substring(from, to);
		//函数式接口中实现方法的第一个参数作为调用者
		//后面的参数都传给该方法作为参数
		converter = String::substring;
		System.out.println(converter.convert("Hello```aaaxzc", 1, 4));
		
		//引用构造器
		JframeTest test = JFrame::new;
		test.convert("哈哈").show();;
		
		String[] texts = {"fkjava","moekee","ssss","wang"};
		Arrays.parallelSort(texts,(o1,o2)-> o1.length() - o2.length());
	}
	
}

@FunctionalInterface
interface Converface{
	Integer valueOf(String from);
}
@FunctionalInterface
interface Converter{
	String convert(String text,int from,int to);
}
@FunctionalInterface
interface JframeTest{
	JFrame convert(String title);
}
