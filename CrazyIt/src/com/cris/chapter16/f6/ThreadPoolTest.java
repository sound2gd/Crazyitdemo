package com.cris.chapter16.f6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试Java5之后新增的线程池支持
 * 
 * @author cris
 *
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		// 1，首先，通过Executors得到一个线程池对象
		ExecutorService pool = Executors.newFixedThreadPool(2);
		// 2，定义一个任务
		Runnable target = () -> {
			for (int i = 0; i < 200; i++) {
				System.out.println(Thread.currentThread().getName() + ",i=" + i);
			}
		};
		// 3,向线程池提交任务
		pool.submit(target);
		pool.submit(target);
		// 4，关闭线程池
		pool.shutdown();
	}

}
