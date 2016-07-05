package com.cris.chapter16.f6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 使用Java7新增的ForkJoinPool执行有返回值的任务
 * 
 * @author cris
 *
 */
public class ForkJoinPoolTest2 {

	public static void main(String[] args) throws Exception{
		//创建一个通用线程池
		ForkJoinPool pool = ForkJoinPool.commonPool();
		int[] arr = new int[100];
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			arr[i - 1] = i;
			sum += i;
		}
		System.out.println("正确的结果是:" + sum);
		// 提交任务
		Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
		System.out.println("执行计算的结果为:"+future.get());
		pool.shutdown();
	}
}

class CalTask extends RecursiveTask<Integer> {

	// 定义每个小任务仅仅累加20个数
	private static final int THRESHHOLD = 20;
	private int arr[];
	private int start;
	private int end;

	@Override
	protected Integer compute() {
		int sum = 0;
		// 当end与start之间的差小于THRESHHOLD，既要累加的数超过20个，将大任务分别成俩小任务
		if (end - start > THRESHHOLD) {
			for (int i = start; i < end; i++) {
				sum += arr[i];
			}
			return sum;
		} else {
			int middle = (start + end) / 2;
			CalTask ca1 = new CalTask(arr, start, middle);
			CalTask ca2 = new CalTask(arr, middle, end);
			// 并行执行俩小任务
			ca1.fork();
			ca2.fork();
			// 将得到的结果累加
			return ca1.join() + ca2.join();
		}
	}

	// 累加从start到end的arr数组元素
	public CalTask(int[] arr, int start, int end) {
		super();
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

}
