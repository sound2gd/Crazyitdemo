package com.cris.chapter07;

import java.io.File;
import java.util.Scanner;

/**
 * 测试Scanner类
 * Scanner类可以从文件，输入流，字符串中
 * 解析出基本类型值和字符串值
 * @author cris
 *
 */
@SuppressWarnings("resource")
public class ScannerTest {

	public static void main(String[] args) throws Exception {
		//从标准输入中得到数据 System.in代表标准输入，也就是键盘输入
		Scanner sc = new Scanner(System.in);
		//设置分隔符
//		sc.useDelimiter("\n");
		while(sc.hasNext()){
			String next = sc.next();
			System.out.println("您刚才输入的是:"+next);
			if ("exit".equals(next)){
				System.out.println("退出scanner循环!");
				break;
			}
		}
		//Scanner的读取操作可能被阻塞来等待信息的输入
		
		//Scanner读取文件作为输入流
		sc = new Scanner(new File("src/com/cris/chapter07/ScannerTest.java"));
		System.out.println("ScannerTest.java文件的内容如下:");
		while(sc.hasNext()){
			System.out.println(sc.nextLine());
		}
	}
	
}
