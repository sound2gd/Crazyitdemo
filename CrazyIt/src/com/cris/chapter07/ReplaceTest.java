package com.cris.chapter07;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试String类的Replace支持
 * 
 * @author cris
 *
 */
public class ReplaceTest {

	public static void main(String[] args) {
		String[] msgs = { "Java has regular expressions in 1.4", "regular expression now expressing in Java",
				"Java represses oracular expressions", };

		Pattern pattern = Pattern.compile("re\\w+");
		Matcher mc = null;
		for (String msg : msgs) {
			if (mc == null) {
				mc = pattern.matcher(msg);
			} else {
				mc.reset(msg);
			}
			
			System.out.println(mc.replaceAll("哈哈"));
		}

		
		//------String类的replace-------
		for (String msg : msgs) {
			System.out.println(msg.replaceFirst("re\\w+", "哈哈"));
			System.out.println(Arrays.toString(msg.split(" ")));
		}
		
	}

}
