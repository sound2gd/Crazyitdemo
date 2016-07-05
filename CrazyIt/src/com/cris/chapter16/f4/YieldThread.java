package com.cris.chapter16.f4;

/**
 * 测试yield
 * 
 * @author cris
 *
 */
public class YieldThread extends Thread {

	public YieldThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println(Thread.currentThread().getName() + ",i=" + i);
			
			//如果循环变量i=20,让步
			if(i==20){
				Thread.yield();
			}
		}
	}

	public static void main(String[] args) {
		// 启动两个并发线程
		YieldThread yt1 = new YieldThread("高级");
		YieldThread yt2 = new YieldThread("低级");

		yt1.setPriority(MAX_PRIORITY);
		yt2.setPriority(MIN_PRIORITY);

		yt1.start();
		yt2.start();
	}

}
