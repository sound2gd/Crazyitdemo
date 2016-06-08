package com.cris.chapter08;

import java.util.EnumSet;

/**
 * 测试EnumTest的用法<br>
 * EnumSet没有暴露任何构造器来创建该类的实例<br>
 * 只能通过类方法来创建EnumSet<br>
 * EnumSet是Set中性能最好的类
 * @author cris
 *
 */
public class EnumSetTest {
		
	public static void main(String[] args) {
		//创建一个EnumSet集合，集合元素就是Season枚举类的全部枚举值
		EnumSet<Season> es1 = EnumSet.allOf(Season.class);
		System.out.println(es1);

		//创建空集合
		EnumSet<Season> es2 = EnumSet.noneOf(Season.class); 
		//手动添加元素
		es2.add(Season.FALL);
		es2.add(Season.FALL);
		es2.add(Season.SUMMER);
		System.out.println(es2);
		
		//指定枚举值创建集合
		EnumSet<Season> es3 = EnumSet.of(Season.SPIRNG,Season.WINTER);
		System.out.println(es3);
		//求上面集合的补集
		EnumSet<Season> es5 = EnumSet.complementOf(es3);
		System.out.println(es5);
		
	}
	
}

enum Season{
	SPIRNG,SUMMER,FALL,WINTER
}