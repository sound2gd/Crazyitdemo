package com.cris.chapter08;

import java.util.WeakHashMap;

/**
 * 测试WeakHashMap的用法<br>
 * 和HashMap不一样，HashMap中的键是强引用，只要HashMap对象还存在，就不会被回收<br>
 * WeakHashMap的键都是弱引用，一旦垃圾回收将全部回收<br>
 * 不要让key所对应的对象具有任何强引用，否则将失去WeakHashMap的意义
 * 
 * @author cris
 *
 */
public class WeakHashMapTest {

	public static void main(String[] args) {
		WeakHashMap<String, String> wk =new WeakHashMap<>();
		wk.put(new String("语文"), "哈哈");
		wk.put(new String("数学"), "哈哈");
		wk.put(new String("英语"), "哈哈");
		wk.put("生物", "哈哈");
		wk.put("化学", "哈哈");
		
		System.out.println(wk);
		//通知系统垃圾回收
		System.gc();
		System.runFinalization();
		//由于"生物""化学"是字符串直接量，会被系统缓存，所以不会被回收
		System.out.println(wk);
	}
	
}
