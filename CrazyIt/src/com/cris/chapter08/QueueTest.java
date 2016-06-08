package com.cris.chapter08;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 测试Queue<br>
 * Queue是java中对于队列的实现,队列是一种先进先出(FIFO)的结构<br>
 * Queue的常用实现类有PriorityQueue<br>
 * Queue有一个子接口Deque，是一个双端队列，也可以当做栈(Stack)用
 * 
 * @author cris
 *
 */
public class QueueTest {

	public static void main(String[] args) {
		// 创建一个队列
		Queue<String> queue = new PriorityQueue<>();
		// PriorityQueue不是一个完全标准的队列，因为会对其内部的元素进行排序
		queue.add("Queue2");
		queue.add("Queue1");
		queue.add("Queue4");
		queue.add("Queue3");
		queue.add("Queue5");
		queue.add("Queue6");
		queue.add("Queue7");

		// 输出该队列
		System.out.println(queue);
		// 取出第一个元素,并从队列中删除
		System.out.println(queue.poll());
		// 添加元素到队列尾部，由于PriorityQueue会排序所以会添加到最队列头
		queue.offer("FkJava");
		// 取出第一个元素，不删除
		System.out.println(queue.peek());

		System.out.println(queue);

		// 指定排序规则创建一个PriorityQueue
		Queue<String> queue2 = new PriorityQueue<>((obj1, obj2) -> {
			char[] c1 = obj1.toCharArray();
			char[] c2 = obj2.toCharArray();

			return c1[obj1.length() - 1] > c2[obj2.length() - 1] ? -1
					: c1[obj1.length() - 1] == c2[obj2.length() - 1] ? 0 : 1;
		});
		queue2.add("Queue2");
		queue2.add("Queue1");
		queue2.add("Queue4");
		queue2.add("Queue3");
		queue2.add("Queue5");
		queue2.add("Queue6");
		queue2.add("Queue7");
		
		System.out.println(queue2);
	}

}
