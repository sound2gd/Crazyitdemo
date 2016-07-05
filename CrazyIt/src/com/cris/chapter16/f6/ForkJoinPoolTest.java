package com.cris.chapter16.f6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 测试Java7新增的ForkJoinPool线程池
 * 
 * @author cris
 *
 */
public class ForkJoinPoolTest {

	public static void main(String[] args) throws Exception{
		ForkJoinPool fkj = new ForkJoinPool();
		//提交可分解的PrintTask任务
		fkj.submit(new PrintTask(0,300));
		fkj.awaitTermination(2, TimeUnit.SECONDS);
		//关闭线程池
		fkj.shutdown();
	}
}

class PrintTask extends RecursiveAction {

	// 定义每个小任务最多打印50个值
	private static final int THRESHHOLD = 50;
	private int start;
	private int end;

	public PrintTask(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if (end - start < THRESHHOLD) {
			for (int i = start; i < end; i++) {
				System.out.println(Thread.currentThread().getName() + ",i=" + i);
			}
		} else {
			// 如果打印的数超过`THRESHHOLD`个则拆分成小任务
			// 将大任务拆分成俩小任务
			int middle = (end + start) / 2;
			PrintTask pr1 = new PrintTask(start, middle);
			PrintTask pr2 = new PrintTask( middle,end);
			//并行执行俩小任务
			pr1.fork();
			pr2.fork();
		}
	}

}
