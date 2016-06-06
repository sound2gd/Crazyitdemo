package com.cris.chapter07;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;

/**
 * 测试java8新增的时间类<br>
 * <ul>
 * 		<li>Clock:用于获取指定时区的当前日期，时间，可以用于取代system.currrentTimeMillis</li>
 * 		<li>Duration:代表持续时间,可以方便的获取一段时间</li>
 * 		<li>Instant:代表一个具体的时刻,可以精确到纳秒</li>
 * 		<li>LocalDate:该类代表不带时区的日期,例如2003-04-01</li>
 * 		<li>LocalTime:该类代表不带时区的时间，例如10:15:33</li>
 * 		<li>LocalDateTime:该类代表不带时区的日期，时间</li>
 * 		<li>MonthDay:代表月日</li>
 * 		<li>Year:代表年</li>
 * 		<li>YearMonth:代表年月</li>
 * 		<li>ZoneDateTime:时区化的日期，时间</li>
 * 		<li>ZoneId:该类代表一个时区</li>
 * 		<li>DayOfWeek:枚举类，代表一周的具体星期</li>
 * 		<li>Month:枚举类，代表月份</li>
 * </ul>
 * 
 * @author cris
 *
 */
public class NewDatePackageTest {

	public static void main(String[] args) {
		
		//下面是关于Clock的用法
		Clock clock =Clock.systemUTC();
		//通过clock获取当前时间
		System.out.println("当前时刻为:"+clock.instant());
		//获取毫秒数
		System.out.println("当前毫秒数为:"+clock.millis());
		System.out.println("系统毫秒数:"+System.currentTimeMillis());
		
		//--------------Duration用法----------------
		Duration duration = Duration.ofSeconds(6000);
		System.out.println("6000秒相当于:"+duration.toMinutes()+"分");
		System.out.println("6000秒相当于:"+duration.toDays()+"天");
		System.out.println("6000秒相当于:"+duration.toHours()+"小时");
		System.out.println("6000秒相当于:"+duration.toMillis()+"毫秒");
		
		//在clock上增加6000秒，返回新的clock
		Clock clock2 = Clock.offset(clock, duration);
		System.out.println("当前时刻为:"+clock2.instant());
		
		//-------Instant的用法-------------
		//获取当前时间
		Instant instant = Instant.now();
		System.out.println(instant);
		//instant往后加６０００秒
		System.out.println(instant.plusSeconds(6000));
		//根据字符串解析instant对象
		Instant instant2 = Instant.parse("2016-06-06T11:44:33Z");
		System.out.println(instant2);
		//获取instant2　５天以前时间
		System.out.println(instant2.minus(Duration.ofDays(5)));
		
		//---------localDate的使用----------
		//返回当前日期
		LocalDate localdate = LocalDate.now();
		System.out.println(localdate);//2016-06-06
		//获得2014年的第146天
		System.out.println(LocalDate.ofYearDay(2014, 146));
		
		//----------localTime的使用--------------
		//获取当前时间
		LocalTime localtime = LocalTime.now();
		System.out.println(localtime);//19:58:33.521
		
		//返回一天中的第７８９９秒
		System.out.println(LocalTime.ofSecondOfDay(7899));
		
		//---------LocalDateTime的使用-----------------
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);//2016-06-06T20:00:52.553
		
		//当前时间加上1天２５小时3分钟
		System.out.println(localDateTime.plusDays(1).plusHours(25).plusMinutes(3));
		
		//---------Year,YearMonth,MonthDay的用法------------
		Year year =Year.now();
		System.out.println(year);//2016
		
		YearMonth ym = year.atMonth(3);
		System.out.println(ym);//2016-03
		System.out.println(ym.plusYears(1).plusMonths(3));//2017-06
		
		MonthDay monthDay = MonthDay.now();
		System.out.println("当前月日:"+monthDay);//--06-06
		
		
	}
	
}
