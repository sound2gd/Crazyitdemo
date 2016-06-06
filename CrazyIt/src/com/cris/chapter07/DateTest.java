package com.cris.chapter07;

import java.util.Date;

/**
 * 测试Date类<br>
 * 一般来说不建议使用Date类<br>
 * 大部分方法都已经过时了<br>
 * jdk1.8之前推荐使用Calendar类<br>
 * 但是这个类也有逻辑问题，比如进位
 * @author cris
 *
 */
public class DateTest {
	
	public static void main(String[] args) {
		Date date = new Date();
		Date date2 = new Date(System.currentTimeMillis()+100);
		System.out.println("currentTimemillis:"+date.getTime());
		//compareTo 大于返回大于０的数，小于返回小于０的数，等于返回０
		System.out.println(date.compareTo(date2));
		System.out.println(date.before(date2));
	}
	
}
