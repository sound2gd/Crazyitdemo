package com.cris.chapter15.f6;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 测试FileChannel模拟传统IO用竹筒多次取水的过程
 * 
 * @author sound2gd
 *
 */
public class FileChannelTest2 {

	public static void main(String[] args) throws Exception{
		FileInputStream sr = new FileInputStream("src/com/cris/chapter15/f6/FileChannelTest2.java");
		FileChannel fc = sr.getChannel();
		ByteBuffer bf = ByteBuffer.allocate(256);
		
		//创建Charset对象
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		
		while((fc.read(bf))!=-1){
			//锁定Buffer的空白区
			bf.flip();
			//转码
			CharBuffer cbuff = decoder.decode(bf);
			System.out.print(cbuff);
			//buffer初始化，用于下一次读取
			bf.clear();
			
		}
		
	}
	
}
