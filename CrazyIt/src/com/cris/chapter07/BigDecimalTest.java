package com.cris.chapter07;

import java.math.BigDecimal;

/**
 * BigDecimal测试<br>
 * 不使用BigDecimal类会发生精度丢失<br>
 * 使用BigDecimal类可以避免
 * @author cris
 *
 */
public class BigDecimalTest {

	public static void main(String[] args) {
		//推荐　使用String构造器
		BigDecimal f1 = new BigDecimal("1.2");
		BigDecimal f2 = new BigDecimal("0.2");
		//直接使用double构造器会发生精度损失
		BigDecimal f3 = new BigDecimal(0.2);
		
		//使用BigDecimal进行四则运算
		System.out.println(f1.add(f2));
		System.out.println(f1.min(f2));
		System.out.println(f1.multiply(f2));
		System.out.println(f1.divide(f2));
		
		System.out.println(f1.add(f3));
		System.out.println(f1.min(f3));
		System.out.println(f1.multiply(f3));
		System.out.println(f1.divide(f3));
	}
	
}
