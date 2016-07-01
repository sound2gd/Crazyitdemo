package com.cris.chapter15.f3;

import java.io.FileInputStream;
import java.io.SequenceInputStream;

/**
 * 测试SequenceInputStream<br>
 * <h1>这个类从JDK1.0开始就存在了，太过于古老，不推荐使用</h1>
 * 
 * @author cris
 *
 */
public class SequenceInputStreamTest {

	public static void main(String[] args) throws Exception {
		// 从一个InputStream的集合或者俩InputStream中生成一个顺序流
		SequenceInputStream sis = new SequenceInputStream(new FileInputStream("src/com/cris/rmi/HelloClient.java"),
				new FileInputStream("src/com/cris/rmi/HelloServer.java"));

		// 顺序流会依次读取每一个输入流的数据
		byte[] buffer = new byte[1024];
		int length = 0;

		while ((length = sis.read(buffer)) != -1) {
			System.out.println(new String(buffer, 0, length));
		}
	}

}
