package com.cris.chapter08;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试Map的基本用法<br>
 * Java中Map是用于保存有映射关系的数据(key-value对)<br>
 * Java中Map和Set的关系很密切，因为Set就是Value为空Object的Map
 * 
 * @author cris
 *
 */
public class MapTest {

	public static void main(String[] args) {
		// 创建一个Map集合
		Map<String, Integer> map = new HashMap<>();
		// 通过put方法可以往Map集合中放入键值对
		map.put("语文", 110);
		// value可以重复，key值重复将会覆盖
		map.put("数学", 150);
		map.put("英语", 150);
		map.put("化学", 90);
		map.put("生物", 70);
		map.put("生物", 80);

		System.out.println(map);
		// 判断是否包含指定的key
		System.out.println("是否包含'数学'?" + map.containsKey("数学"));
		// 判断是否包含60的值
		System.out.println("是否包含值为'60'?" + map.containsValue(60));

		// 使用remove方法根据键值对来删除Map中指定的键值对
		// 下面是JDK1.8新增，键值对有一个不符合集合中的元素将删除失败
		// 判断相等的标准是equals
		map.remove("生物", 80);

		// 根据键来删除
		// map.remove("生物");

		// 使用forEach来遍历
		for (String key : map.keySet()) {
			System.out.println("key:" + key + "--->value:" + map.get(key));
		}

	}

}
