package com.cris.chapter07;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试Matcher方法
 * 
 * @author cris
 *
 */
public class MatcherTest {

	public static void main(String[] args) {
		String[] mails = { "kuihksj@163.com", "kongyeeku@gmail.com", "ligang@crazyit.com", "wawa.xx", };

		String mailRegex = "\\w{3,20}@\\w+\\.(com|cn|edu|gov|net|org)";
		Pattern pattern = Pattern.compile(mailRegex);
		Matcher mc = null;
		for (String mail : mails) {
			if (mc == null) {
				mc = pattern.matcher(mail);
			}else{
				//将现有的Matcher对象以及规则
				//应用于新的字符序列去匹配(重复利用同一个对象)
				mc.reset(mail);
			}
			
			String result = mail+(mc.matches()?" 是":" 不是")+" 一个有效的邮件地址";
			System.out.println(result);
		}
	}

}
