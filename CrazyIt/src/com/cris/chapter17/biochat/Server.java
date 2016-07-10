package com.cris.chapter17.biochat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BIO聊天服务器的主类，负责监听客户端的请求，并创建线程
 * 
 * @author cris
 *
 */
public class Server {

	// 定义保存所有的Socket的ArrayList,并将其包装成线程安全的
	public static List<Socket> list = Collections.synchronizedList(new ArrayList<>());

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(8888);
		//持续监听
		while (true) {
			// 此行代码会阻塞，一直等待别人的链接
			Socket socket = ss.accept();
			list.add(socket);
			// 每当客户端连接后为其启动一个线程服务
			new Thread(new ServerThread(socket)).start();
		}

	}

}

class ServerThread extends Thread {

	// 定义当前处理的Socket
	Socket s = null;
	BufferedReader br = null;

	public ServerThread(Socket s) throws IOException {
		super();
		this.s = s;
		// 初始化该Socket对应的输入流
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		try {
			String content = null;
			// 采用循环不断的读取Socket中客户端发过来的数据
			while ((content = readFromClient()) != null) {
				// 遍历socketList的每一个Socket
				// 将读取到的内容向每个Socket发送一次
				for (Socket temp : Server.list) {
					PrintStream printStream = new PrintStream(temp.getOutputStream());
					printStream.println(content);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 定义读取客户端数据的方法
	private String readFromClient() {
		try {
			return br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
			// 将出错的Socket删掉
			Server.list.remove(s);
		}
		return null;
	}
}
