package com.cris.chapter07;

import java.util.Calendar;
import java.util.Date;

/**
 * 测试Calendar类<br>
 * 
 * @author cris
 *
 */
public class CalendarTest {

	public static void main(String[] args) {
		//Calendar.getInstance可以得到一个实例，注意，不是单例
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		System.out.println(date);
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH));//月份
		System.out.println(calendar.get(Calendar.DATE));
		System.out.println(calendar.get(Calendar.HOUR));
		System.out.println(calendar.get(Calendar.MINUTE));
		System.out.println(calendar.get(Calendar.SECOND));
		
		//2003-08-31
		calendar.set(2003, 7, 31, 23, 11, 11);
		System.out.println(calendar.getTime().toLocaleString());
		//add方法会进位，但是不准确，正常来说
		//结果是2004-03-02
		calendar.add(Calendar.MONDAY, 6);
		System.out.println(calendar.getTime().toLocaleString());
		
		//roll不会进位
		calendar.set(2003, 7, 31, 23, 11, 11);
		calendar.roll(Calendar.MONDAY, 6);
		System.out.println(calendar.getTime().toLocaleString());
		
		//不管是set,还是roll,在没有下一次get之前都没有真正进行时间操作
		calendar.set(2003, 7, 31, 23, 11, 11);
		//变成10月１号
		calendar.set(Calendar.MONDAY, 8);
//		System.out.println(calendar.getTime().toLocaleString());
		calendar.set(Calendar.DATE,20);
		//得到9月20号
		System.out.println(calendar.getTime().toLocaleString());
		
		//设置容错性,默认是true,会把大于１２的转成对应的正确数字
		calendar.setLenient(false);
		calendar.set(Calendar.MONDAY, 14);
		System.out.println(calendar.getTime().toLocaleString());
	}
	
}
