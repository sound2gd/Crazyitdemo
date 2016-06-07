package com.cris.chapter07;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * 测试Java8新增的DateTimeFormatter
 * 
 * @author cris
 *
 */
public class NewFormatterTest {

	public static void main(String[] args) {
		DateTimeFormatter[] formatters = new DateTimeFormatter[] {
				// 直接使用常量创建DateTimeFormatter格式量
				DateTimeFormatter.ISO_LOCAL_DATE, DateTimeFormatter.ISO_LOCAL_TIME,
				DateTimeFormatter.ISO_LOCAL_DATE_TIME,

				// 使用本地化的不同风格来创建DateTimeFormatter实例
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM),
				DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG),

				// 根据模式字符串来创建DateTimeFormatter
				DateTimeFormatter.ofPattern("Gyyyy%%MMM%%dd HH:mm:ss") };

		LocalDateTime date = LocalDateTime.now();
		for (int i = 0; i < formatters.length; i++) {
			// 下面两行的作用相同
			System.out.println(date.format(formatters[i]));
			System.out.println(formatters[i].format(date));
		}

		// 字符串转LocalDateTime
		String time = "2016年===06===07 08点44时22秒";
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy年===MM===dd HH点mm时ss秒");
		LocalDateTime parse = LocalDateTime.parse(time,dt);
		System.out.println(parse);
	}

}
