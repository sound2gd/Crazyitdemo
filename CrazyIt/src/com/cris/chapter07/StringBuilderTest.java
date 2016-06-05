package com.cris.chapter07;
/**
 * 测试StringBuilder中的基本方法
 * 
 * @author cris
 *
 */
public class StringBuilderTest {
	public static void main(String[] args) {
		StringBuilder sb =new StringBuilder();
		//追加字符串
		sb.append("java");
		//插入
		sb.insert(0, "Hello ");
		//替换
		sb.replace(0, 2, "AAAA");
		//删除
		sb.delete(4, 5);
		System.out.println(sb);
		//反转
		sb.reverse();
		System.out.println(sb);
		System.out.println(sb.length());
		System.out.println(sb.capacity());
		//设置长度，仅截取前面几个
		sb.setLength(5);
		System.out.println(sb);
	}
}
