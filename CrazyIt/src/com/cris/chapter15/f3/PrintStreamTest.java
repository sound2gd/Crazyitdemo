package com.cris.chapter15.f3;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * 测试处理流PrintStream的用法
 * 
 * @author sound2gd
 *
 */
public class PrintStreamTest {

	public static void main(String[] args) {
		try (OutputStream o = new FileOutputStream("src/com/cris/chapter15/f3/print.txt");
				//处理流需要传入一个节点流参数
				PrintStream pr = new PrintStream(o);) {
			pr.print("普通字符串");
			//仅调用了toString方法向文件打印了字符串
			//并没有将对象写入一个文件中
			pr.print(new PrintStreamTest());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
