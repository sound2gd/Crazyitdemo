package com.cris.chapter15.f6;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.SortedMap;

/**
 * 测试JDK1.4以后提供的Charset以及Charset的编解码器
 * 
 * @author sound2gd
 *
 */
public class CharsetTest {

	public static void main(String[] args) throws Exception{
		//获取所有可用的Charset
		SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		for(Map.Entry<String, Charset> entry:availableCharsets.entrySet()){
			System.out.println(entry.getKey()+"--->"+entry.getValue());
		}
		
		//创建Charset
		Charset charset = Charset.forName("UTF-8");
		//得到对应的编解码器
		CharsetEncoder newEncoder = charset.newEncoder();
		CharsetDecoder newDecoder = charset.newDecoder();
		
		//创建一个CharBuffer
		CharBuffer cb = CharBuffer.allocate(8);
		cb.put("孙");
		cb.put("悟");
		cb.put("空");
		cb.flip();
		
		//将CharBuffer中的字符序列转成字节序列
		ByteBuffer bbuff = newEncoder.encode(cb);
		System.out.println(bbuff.capacity());
		System.out.println(bbuff.limit());
		//访问每个字节
		for (int i = 0; i < bbuff.limit(); i++) {
			System.out.print(bbuff.get(i)+" ");
		}
		//ByteBUffer转成字符
		System.out.println("\n"+newDecoder.decode(bbuff));
		
	}
	
}
