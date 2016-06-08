package com.cris.chapter08;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Deque是Queue的子接口，<br>
 * 它即可以当成双端队列使用，也可以当成栈使用,还可以当成单队列使用
 * 
 * @author cris
 *
 */
public class DequeTest {

	public static void main(String[] args) {
		//Deque的常用实现类是ArrayDeque和LinkedList
		Deque<Integer> deque = new ArrayDeque<>();
		
		//------------当成栈用----------------------
		//先进后出(FILO)
		deque.push(2);
		deque.push(4);
		deque.push(3);
		deque.push(6);
		deque.push(5);
		System.out.println(deque);
		
		//访问栈顶的第一个元素,但是不删除
		System.out.println(deque.peek());
		//访问栈顶的第一个元素，删除
		System.out.println(deque.pop());
		
		//------------单队列--------------------------
		//先进先出
		deque = new ArrayDeque<>();
		deque.offer(2);
		deque.offer(4);
		deque.offer(3);
		deque.offer(5);
		deque.offer(26);
		deque.offer(7);
		
		//取出队列的第一个元素,不删除
		System.out.println(deque.peek());
		//取出队列的第一个元素,删除
		System.out.println(deque.pop());
		System.out.println(deque);
		
		
		//------------双端队列-------------------------
		deque = new ArrayDeque<>();
		//在队列头插入元素
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addFirst(3);
		deque.addFirst(4);
		deque.addFirst(5);
		deque.addFirst(6);
		deque.addFirst(7);
		//在队列尾插入元素
		deque.addLast(8);
		deque.addLast(9);
		deque.addLast(10);
		deque.addLast(11);
		deque.addLast(12);
		deque.addLast(13);
		//默认在队列的尾端加入
		deque.add(14);
		
		System.out.println(deque);
		
	}
	
}
