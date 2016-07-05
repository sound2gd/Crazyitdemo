package com.cris.chapter16.f2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 测试使用Callable和FutureTask来创建线程类
 * 
 * @author cris
 *
 */
public class ThirdThread {

	public static void main(String[] args) {
		// 创建Callable对象
		Callable<Integer> callable = () -> {
			int i = 0;
			for (; i < 50; i++) {
				System.out.println(Thread.currentThread().getName() + " , i= " + i);
			}
			return i;
		};
		// 使用FutureTask来包装Callable对象
		FutureTask<Integer> fu = new FutureTask<>(callable);

		for (int i = 0; i < 50; i++) {
			if (i == 20) {
				new Thread(fu, "有返回值的线程").start();
			}
		}

		// 获得线程的返回值
		try {
			//FutureTask对象的get方法将会导致本线程阻塞，等待子线程的返回值
			System.out.println("子线程的返回值:" + fu.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
