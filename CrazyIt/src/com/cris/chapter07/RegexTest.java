package com.cris.chapter07;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试Java正则表达式<br>
 * Java的String类的replaceFirst,replaceAll,split,matches等都支持正则<br>
 * 此外，还提供了Pattern和Matcher来支持正则
 * @author cris
 *
 */
public class RegexTest {

	public static void main(String[] args) {
		//将正则规则导入 匹配十一位的手机号
		Pattern pattern1 = Pattern.compile("(13\\d|15\\d|18\\d)\\d{8}");
		//匹配
		String str = "我想学java,我的手机号"
				+ "是13100000000,地址是北京市中关村１号,"
				+ "有向交朋友的，联系18912334566";
		Matcher matcher1=pattern1.matcher(str);
		while(matcher1.find()){
			System.out.println(matcher1.group());
			/**
			 * 输出
			 * 13100000000
			 * 18912334566
			 */
		}
		
		String str2="Ｊａｖａ　ｉｓ　ｖｅｒｙ　ｅａｓｙ";//中文全角无法匹配
		str2="Java is very Easy!!";//英文可以匹配 \w+
		System.out.println("目标字符串:"+str2);
		Matcher mc = Pattern.compile("\\w+").matcher(str2);
		while(mc.find()){
			//通过start和end方法可以得到匹配字串的开始位置和结束位置
			System.out.println("子串的起始位置："+mc.start()+",子串的结束位置:"+mc.end());
		}
		
		
	}

}
