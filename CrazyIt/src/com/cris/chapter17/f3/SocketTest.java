package com.cris.chapter17.f3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Socket端，测试ServerSocket和Socket TCP通信
 * 
 * @author cris
 *
 */
public class SocketTest {

	public static void main(String[] args) throws Exception {
		Socket s = new Socket();
		s.connect(new InetSocketAddress("localhost", 8888), 1000);// 设置超时
		
		// 将输入流包装成BufferedReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));

		// 读取
		System.out.println(reader.readLine());

		// 关闭输入流，关闭Socket
		reader.close();
		s.close();
	}

}
