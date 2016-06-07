package com.cris.chapter08;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 测试Java8提供的流式API
 * @author cris
 *
 */
public class IntStreamTest {

	public static void main(String[] args) {
		//构造int流，Java8新增的流API都提供了builder
		IntStream is = IntStream.builder()
				.add(20)
				.add(-13)
				.add(33)
				.add(10)
				.add(20)
				.build();
		//以下方法都是intermediate方法，使用不会消费流
		is=is.map(ele -> ele*2+1);
		is=is.sorted();
		
		//以下都是terminate方法，使用以下方法会导致流被消费
//		System.out.println(Arrays.toString(is.toArray()));
//		System.out.println("所有元素的最大值:"+is.max().getAsInt());
//		System.out.println("所有元素的最小值:"+is.min().getAsInt());
//		System.out.println("所有元素的总和:"+is.sum());
//		System.out.println(is.allMatch(ele -> ele>0));
//		System.out.println(is.anyMatch(ele -> ele <0));
		is.forEach(System.out::println);
	}
	
}
