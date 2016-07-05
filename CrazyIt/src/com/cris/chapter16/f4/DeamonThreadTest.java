package com.cris.chapter16.f4;

/**
 * 测试Deamon Thread的用法
 * @author cris
 *
 */
public class DeamonThreadTest {

	public static void main(String[] args) {
		Runnable ra = () ->{for (int i = 0; i < 200; i++) {
			System.out.println(i);
		}};
		
		Thread tr =new Thread(ra);
		//使用setDaemon可以设置为守护线程
		tr.setDaemon(true);
		tr.start();
		
		//可以看到，并没有输出200个数，只输出了几个，当主线程结束之后，该守护线程也终结了
	}
	
}
