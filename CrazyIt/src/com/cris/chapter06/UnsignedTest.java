package com.cris.chapter06;
/**
 * 测试Java8新增的包装类对无符号运算的支持
 * @author cris
 *
 */
public class UnsignedTest {

	public static void main(String[] args) {
		
		byte number = -3;
		//将number转换成无符号整数对应的字符串
		System.out.println("-3对应的无符号整数是:"+Byte.toUnsignedInt(number));//253
		//指定使用十六进制解析无符号整数
		int val = Integer.parseUnsignedInt("ab", 16);
		System.out.println(val);//171
		//将-12转换成无符号int型，然后转换成无符号int型
		System.out.println(Integer.toUnsignedString(-12, 16));//fffffff4
		//将两个数转换成无符号整数后相除
		System.out.println(Integer.divideUnsigned(-2, 3));//1431655764
		//将两个数转换成无符号整数相除后求余
		System.out.println(Integer.remainderUnsigned(-2, 7));//2
		
	}
}
