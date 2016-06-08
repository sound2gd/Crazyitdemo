package com.cris.chapter08;

import java.util.IdentityHashMap;

/**
 * 测试IdentityHashMap的用法<br>
 * IdentityHashMap和Map没有明显的性能区别<br>
 * 只是比较key是否相等加了一个规则：如果obj1 == obj2，才认为key相等
 * 
 * @author cris
 *
 */
public class IdentityHashMapTest {
	
	public static void main(String[] args) {
		
		IdentityHashMap<String, String> ihm = new IdentityHashMap<>();
		//以下可以得知规则是==和hashCode相等,key才相等
		ihm.put(new String("语文"), "庄子");
		ihm.put(new String("语文"), "庄子2");
		ihm.put("语文", "庄子2");
		ihm.put("数学", "拉格朗日1");
		ihm.put("数学", "拉格朗日2");
		
		System.out.println(ihm);//{语文=庄子, 数学=拉格朗日2, 语文=庄子2, 语文=庄子2}
		
		
	}
	
}
