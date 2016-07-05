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
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Runnable target = ()->{
			for (int i = 0; i < 200; i++) {
				System.out.println(Thread.currentThread().getName()+",i="+i);
			}
		};
		pool.submit(target);
		pool.submit(target);
		pool.shutdown();
	}
	
}
