package com.cris.chapter06;

import java.lang.ref.WeakReference;

/**
 * 测试弱引用
 * 
 * @author cris
 *
 */
public class ReferenceTest {

	public static void main(String[] args) {
		//在堆内存中创建一个字符串对象
		String str = new String("疯狂java讲义");
		//让弱引用引用该对象
		WeakReference<String> wr = new WeakReference<String>(str);
		//断开强引用str和str字符串对象的关联
		str = null;
		//取出弱引用指向的对象
		System.out.println(wr.get());
		//gc
		System.gc();
		//finalization
		System.runFinalization();
		//再次查看弱引用指向的对象
		System.out.println(wr.get());
	}
	
}
