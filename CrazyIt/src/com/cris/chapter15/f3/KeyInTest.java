package com.cris.chapter15.f3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 测试使用转换流将字节流转化为字符流
 * 
 * @author sound2gd
 *
 */
public class KeyInTest {

	public static void main(String[] args) {
		try (
				// 将System.in转化成Reader对象
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);) {
			
			String line = null;
			//采用循环的方式进行读取
			while((line = br.readLine())!=null){
				
				if(line.equals("exit")){
					System.exit(1);
				}
				//打印读取的内容
				System.out.println("输入的内容为:"+line);
			}
			
		} catch (Exception e) {

		}
	}

}
