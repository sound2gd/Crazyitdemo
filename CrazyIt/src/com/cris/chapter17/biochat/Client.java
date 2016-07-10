package com.cris.chapter17.biochat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 聊天服务器客户端
 * @author cris
 *
 */
public class Client {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 8888);
		//客户端启动ClientThread来不断的读取来自服务器的数据
		new Thread(new ClientThread(socket)).start();
		PrintStream ps = new PrintStream(socket.getOutputStream());
		//获取该socket对应的输出流
		String line=null;
		
		//不断读取键盘输入
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		while((line=br.readLine())!=null){
			ps.println(line);
		}
	}
	
}

class ClientThread extends Thread{

	private Socket so;
	private BufferedReader br;
	
	public ClientThread(Socket so) throws Exception{
		super();
		this.so = so;
		br = new BufferedReader(new InputStreamReader(so.getInputStream(), "UTF-8"));
	}

	@Override
	public void run() {
		try {
			String content = null;
			while((content = br.readLine())!=null){
				System.out.println(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}