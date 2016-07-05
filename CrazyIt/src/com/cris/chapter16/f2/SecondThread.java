package com.cris.chapter16.f2;

/**
 * 测试使用Runnable来创建线程类
 * 
 * @author cris
 *
 */
public class SecondThread implements Runnable{

	private int i;
	
	@Override
	public void run() {
		for (; i < 50; i++) {
			System.out.println(Thread.currentThread().getName()+"执行到 "+i);
		}
	}
	
	public static void main(String[] args) {
		SecondThread st1 = new SecondThread();
		SecondThread st2 = new SecondThread();
		
		for (int i = 0; i < 50; i++) {
			if (i==20){
				//真正的线程对象是Thread
				new Thread(st1).start();
				new Thread(st2).start();
			}
		}
	}

}
