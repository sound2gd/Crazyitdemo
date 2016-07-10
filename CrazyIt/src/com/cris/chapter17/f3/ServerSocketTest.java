package com.cris.chapter17.f3;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 测试使用ServerSocket和Socket进行TCP通信
 * 
 * @author cris
 *
 */
public class ServerSocketTest {

	public static void main(String[] args) throws IOException {
		//创建一个ServerSocket,用于监听客户端Socket的请求
		ServerSocket server = new ServerSocket(8888);
		while(true){
			//每次接收到客户端Socket请求时，服务端也对应产生一个Socket
			Socket socket = server.accept();
			//将Socket对应流包装成PrintStream
			PrintStream ps = new PrintStream(socket.getOutputStream());
			//发送信息
			ps.print("您收到了来自Java ServerSocket服务器的新年祝福！");
			
			//关闭输出流，关闭Socket
			socket.close();
			ps.close();
		}
	}
	
}
