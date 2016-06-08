package com.cris.chapter08.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Map的练习<br>
 * 给定字符串数组，使用Map的key来保存数组中的字符元素，value来保存出现的次数
 * 
 * @author cris
 *
 */
public class MapPractice {

	public static void main(String[] args) {
		String[] strs = { "a", "b", "b", "c", "a", "b", "c", "c" };
		// JDK8以前的方法实现
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			String key = strs[i];
			if (!map.containsKey(key)) {
				map.put(key, 1);
			} else {
				Integer value = map.get(key);
				map.put(key, ++value);
			}
		}

		System.out.println(map);

		// JDK8以侯的方法实现
		map = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			String key = strs[i];
			if (!map.containsKey(key)) {
				map.put(key, 1);
			} else {
				map.compute(key, (k,value)-> value+1);
			}
		}

		System.out.println(map);
	}

}
