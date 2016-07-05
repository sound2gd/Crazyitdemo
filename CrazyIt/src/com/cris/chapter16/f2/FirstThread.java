package com.cris.chapter16.f2;

/**
 * 测试使用继承Thread类的方式来启动多线程
 * 
 * @author cris
 *
 */
public class FirstThread extends Thread {// 继承Thread类

	private int i;

	public FirstThread() {
	}

	@Override
	// 重写run方法
	public void run() {
		for (; i < 50; i++) {
			System.out.println("当前线程为:" + Thread.currentThread().getName() + ",执行到 " + i);
		}
	}

	public static void main(String[] args) {

		FirstThread ft = new FirstThread();
		FirstThread ft2 = new FirstThread();

		for (int i = 0; i < 50; i++) {
			System.out.println("主线程的i,i=" + i);

			if (i == 20) {
				// 以下两个线程的输出的i不连续，所以不是同一个变量
				ft.start();
				ft2.start();
			}
		}
	}
}
