package com.cris.chapter16.f3;

/**
 * 试图调用一个已死线程的start方法会出错
 * 
 * @author cris
 *
 */
public class DeadStateTest {

	public static void main(String[] args) {
		StartDead ra = new StartDead();
		ra.start();

		if (Thread.activeCount() > 1) {
			Thread.yield();
		}
		/**
		 * Exception in thread "main" java.lang.IllegalThreadStateException at
		 * java.lang.Thread.start(Thread.java:705) at
		 * com.cris.chapter16.f3.DeadStateTest.main(DeadStateTest.java:20)
		 */
		ra.start();

	}

}

class StartDead extends Thread {

	@Override
	public void run() {
		System.out.println("我是子线程的run方法，我来测试Dead之后能不能复活");
	}
}
