package com.cris.chapter16.f5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 测试使用BlockingQueue实现线程间通信
 * 
 * @author cris
 *
 */
public class BlockingQueueTest {

	public static void main(String[] args) throws Exception{
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
		queue.add("Java");
		queue.offer("Java");
		queue.put("Java");//第三个阻塞
	}
	
}

