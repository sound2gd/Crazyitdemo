package com.cris.chapter16.f5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 测试使用BlockingQueue实现线程间通信
 * 
 * @author cris
 *
 */
public class BlockingQueueTest2 {

	public static void main(String[] args) throws Exception {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
		new Producer(queue, "甲").start();;
		new Producer(queue, "丙").start();;
		new Producer(queue, "乙").start();;
		new Producer(queue, "丁").start();;
		new Consumer(queue, "戊").start();
	}

}

class Producer extends Thread {

	private BlockingQueue<String> queue;

	public Producer(BlockingQueue<String> queue, String name) {
		super(name);
		this.queue = queue;
	}

	@Override
	public void run() {
		final String[] ele = new String[] { "Java", "Python", "JavaScript" };

		for (int i = 0; i < 9999; i++) {
			System.out.println(getName() + "生产者准备生产集合元素!");
			try {
				Thread.sleep(200);
				queue.put(ele[i % 3]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName() + "生产完成!");
	}

}

class Consumer extends Thread{
	private BlockingQueue<String> queue;

	public Consumer(BlockingQueue<String> queue, String name) {
		super(name);
		this.queue = queue;
	}

	@Override
	public void run() {
		while(true){
			System.out.println("消费者准备消费集合元素!");
			try{
				Thread.sleep(200);
				queue.take();
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(getName()+"消费完成:"+queue);
		}
		
	}
}
