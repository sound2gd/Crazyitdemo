package com.cris.chapter15.f6;

import java.nio.CharBuffer;

/**
 * 测试NIO中的Buffer
 * 
 * @author sound2gd
 *
 */
public class BufferTest {

	public static void main(String[] args) {
		// 创建Buffer
		CharBuffer buffer = CharBuffer.allocate(8);
		System.out.println("buffer的容量:" + buffer.capacity());
		System.out.println("buffer的位置:" + buffer.position());
		System.out.println("buffer的界限:" + buffer.limit());

		buffer.put('s');
		buffer.put('o');
		buffer.put('u');
		buffer.put('n');
		buffer.put('d');
		System.out.println("加入5个元素后position:" + buffer.position());

		// 调用flip
		buffer.flip();
		System.out.println("buffer的容量:" + buffer.capacity());
		System.out.println("buffer的位置:" + buffer.position());
		System.out.println("buffer的界限:" + buffer.limit());

		// 取出第一个元素
		System.out.print("buffer中的元素:" + buffer.get());
		while (buffer.hasRemaining()) {
			System.out.print(buffer.get());
		}
		System.out.println();
		System.out.println("取出第一个元素后position=" + buffer.position());

		// 调用clear
		buffer.clear();
		System.out.println("第3个元素" + buffer.get(2));

	}

}
