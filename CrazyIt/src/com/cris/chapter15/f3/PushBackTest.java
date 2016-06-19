package com.cris.chapter15.f3;

import java.io.FileReader;
import java.io.PushbackReader;

/**
 * 测试推回输入流PushBackInputStream<br>
 * 推回输入流是特殊的流，允许将读取的数组推回到缓冲区
 * 
 * @author sound2gd
 *
 */
public class PushBackTest {

	public static void main(String[] args) {

		try (
				// 创建一个PushBackInputStream对象，指定推回缓冲区
				// 的长度为64
				PushbackReader pis = new PushbackReader(new FileReader("src/com/cris/chapter15/f3/PushBackTest.java"),
						64);) {
			char[] buffer = new char[32];
			// 用于保存上次读取的字符串内容
			String lastContent = "";
			int hasRead = 0;

			// 循环读取文件内容
			while ((hasRead = pis.read(buffer)) != -1) {
				// 将读取的内容转换成字符串
				String content = new String(buffer, 0, hasRead);
				int targetIndex = 0;
				// 将上次读取的字符串和本次读取的字符串拼起来
				// 查看是否包含目标字符串，如果包含
				if ((targetIndex = (lastContent + content).indexOf("new PushbackReader")) > 0) {
					//将本次内容和上次内容一起推回缓冲区
					pis.unread((lastContent+content).toCharArray());
					//重新定义一个长度为targetIndex的char数组
					if(targetIndex > 32){
						buffer = new char[targetIndex];
					}
					
					//再次读取指定长度的内容（目标字符串之前的内容）
					pis.read(buffer,0,targetIndex);
					//打印读取的内容
					System.out.println(new String(buffer,0,targetIndex));
				}else{
					//打印上次读取的内容
					System.out.println(lastContent);
					//将本次内容设为上次读取的内容
					lastContent = content;
				}

			}

		} catch (Exception e) {

		}
	}
}
