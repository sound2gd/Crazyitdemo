package com.cris.chapter17.f1;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 测试使用JDK提供的URLEncoder和URLDecoder进行URL编码解码
 * 
 * @author cris
 *
 */
public class URLcoderTest {

	public static void main(String[] args) throws Exception {
		// 将普通字符串转换
		String keyWord = "我是中文";

		// 直接encode方法已经废弃了，一定要指定编码
		String urlencodedStr = URLEncoder.encode(keyWord, "UTF-8");
		System.out.println("'我是中文' URL编码侯的字符串是: " + urlencodedStr);// %E6%88%91%E6%98%AF%E4%B8%AD%E6%96%87

		// 将URL编码侯的字符串转回来
		System.out.println(URLDecoder.decode(urlencodedStr, "UTF-8"));
		
		//每个中文字符占用俩字节，所以每个汉字可以转化成俩十六进制的数字
	}

}
