package com.cris.chapter16.f4;

/**
 * 测试发生死锁的情况
 * 
 * @author cris
 *
 */
public class DeadLock extends Thread {

	private Object a;
	private Object b;
	private boolean is;

	public DeadLock(Object a, Object b, boolean is) {
		super();
		this.a = a;
		this.b = b;
		this.is = is;
	}

	private void runA() {
		synchronized (a) {
			System.out.println("线程成功获得了A对象的锁,正在试图获得b的锁");
			synchronized (b) {
				System.out.println("线程成功获得了B锁");
			}
		}
	}

	private void runB() {
		synchronized (b) {
			System.out.println("线程成功的获得了B对象的锁，正在试图获得A锁");
			synchronized (a) {
				System.out.println("线程成功获得了A锁");
			}
		}
	}

	@Override
	public void run() {
		if (is) {
			runA();
		} else {
			runB();
		}
	}

	public static void main(String[] args) {
		Object a = new Object();
		Object b = new Object();

		DeadLock s1 = new DeadLock(a, b, true);
		DeadLock s2 = new DeadLock(a, b, false);

		// 死锁
		s1.start();
		s2.start();

		// 使用jstack命令得到如下信息
		/**
		 * Found one Java-level deadlock: =============================
		 * "Thread-1": waiting to lock monitor 0x00007f9b3083d2b8 (object
		 * 0x00000007955f4660, a java.lang.Object), which is held by "Thread-0"
		 * "Thread-0": waiting to lock monitor 0x00007f9b3083fca8 (object
		 * 0x00000007955f4670, a java.lang.Object), which is held by "Thread-1"
		 * 
		 * Java stack information for the threads listed above:
		 * =================================================== "Thread-1": at
		 * com.cris.chapter16.f4.DeadLock.runB(DeadLock.java:35) - waiting to
		 * lock <0x00000007955f4660> (a java.lang.Object) - locked
		 * <0x00000007955f4670> (a java.lang.Object) at
		 * com.cris.chapter16.f4.DeadLock.run(DeadLock.java:45) "Thread-0": at
		 * com.cris.chapter16.f4.DeadLock.runA(DeadLock.java:26) - waiting to
		 * lock <0x00000007955f4670> (a java.lang.Object) - locked
		 * <0x00000007955f4660> (a java.lang.Object) at
		 * com.cris.chapter16.f4.DeadLock.run(DeadLock.java:43)
		 * 
		 * Found 1 deadlock.
		 */
	}
}
