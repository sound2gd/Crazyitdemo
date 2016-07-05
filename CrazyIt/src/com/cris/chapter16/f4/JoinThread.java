package com.cris.chapter16.f4;

/**
 * 测试控制线程执行的join的方法
 * 
 * @author cris
 *
 */
public class JoinThread extends Thread {

	@Override
	public void run() {

		for (int i = 0; i < 50; i++) {
			System.out.println(Thread.currentThread().getName() + "子线程正在执行任务,i=" + i);
		}
	}

	public static void main(String[] args) {
		JoinThread thread = new JoinThread();

		for (int i = 0; i < 20; i++) {
			if (i == 5) {
				try {
					thread.start();
					// 这行会阻塞当前线程，直到该线程执行完毕
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("主线程的逻辑,i=" + i);
		}
	}

}
