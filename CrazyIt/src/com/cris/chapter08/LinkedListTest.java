package com.cris.chapter08;

import java.util.LinkedList;

/**
 * 测试LinkedList<br>
 * LinkedList即可以当做List用，又可以当成Deque用,还能当成栈使用
 * @author cris
 *
 */
public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<String> lk = new LinkedList<>();
		//-----当做list用-------------------------
		//添加元素
		lk.add("fkJava1");
		lk.add("fkJava2");
		lk.add("fkJava3");
		lk.add("fkJava4");
		lk.add("fkJava5");
		lk.add("fkJava6");
		lk.add("fkJava7");
		lk.add("fkJava8");
		
		System.out.println(lk);
		System.out.println("第四个元素:"+lk.get(3));
		//---------------当做双端队列使用---------------
		lk.offer("fkJava9");
		lk.addFirst("fkJava10");
		lk.addLast("fkJava11");
		System.out.println(lk);
		System.out.println("队列的第一个元素:"+lk.peek());
		System.out.println("队列的最后一个元素:"+lk.peekLast());
		
		
		//---------------当做栈使用---------------------
		lk.push("fkJava12");
		System.out.println("栈顶的元素"+lk.pop());
		System.out.println(lk);
	}
	
}
