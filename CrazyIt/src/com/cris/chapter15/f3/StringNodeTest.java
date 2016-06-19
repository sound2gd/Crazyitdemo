package com.cris.chapter15.f3;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * 测试StringReader和StringWriter
 * 
 * @author sound2gd
 *
 */
public class StringNodeTest {

	public static void main(String[] args) {
		String src = "从明天起，做一个幸福的人\n" + "喂马，劈材，周游世界\n" + "从明天起，关心粮食和蔬菜\n" + "我有一所房子，面朝大海，春暖花开\n" + "从明天起，和每一个家人通信\n"
				+ "告诉他们我的幸福\n";
		char[] buffer = new char[32];
		int hasRead = 0;

		try (StringReader sr = new StringReader(src)) {
			// 循环读取字符串
			while ((hasRead = sr.read(buffer)) != -1) {
				System.out.println(new String(buffer, 0, hasRead));
			}
		} catch (Exception e) {

		}

		try (
				// 创建一个StringWriter时，实际以一个StringBuffer作为输出节点
				//内部存储了一个StringBuffer成员变量
				StringWriter sw = new StringWriter();) {
			//调用方法执行输出
			sw.write("有一个美丽的新世界,\n");
			sw.write("她在远方等着我,\n");
			sw.write("那里有天真的孩子,\n");
			sw.write("还有姑娘的酒窝,\n");
			System.out.println(sw.toString());
		}catch(Exception e){
			
		}
	}

}
