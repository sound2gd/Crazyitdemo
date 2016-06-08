package com.cris.chapter08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Collections工具类测试<br>
 * <ol>
 * Collections主要有以下功能:
 * <li>排序</li>
 * <li>查找，替换</li>
 * <li>同步控制</li>
 * <li>设置不可变集合</li>
 * </ol>
 * 
 * @author cris
 *
 */
public class CollectionsTest {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(-1);
		list.add(4);
		list.add(7);
		list.add(-12);
		list.add(32);

		// 输出最大值
		System.out.println(Collections.max(list));
		// 输出最小值
		System.out.println(Collections.min(list));
		// 将list中的1使用2代替
		Collections.replaceAll(list, 1, 2);
		System.out.println(list);

		// 排序
		Collections.sort(list);
		System.out.println(list);

		// 只有排序之后的List才能使用二分查找,返回索引
		System.out.println(Collections.binarySearch(list, 2));

		// 同步控制
		// 下面的集合就是安全版本的集合
		Collection<Integer> c = Collections.synchronizedCollection(new ArrayList<>());
		List<Integer> l1 = Collections.synchronizedList(new ArrayList<>());
		Set<Integer> h2 = Collections.synchronizedSet(new HashSet<>());
		Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

		// 设置不可变集合
		// 创建一个新的，不可改变的List对象
		List<Integer> unmodifedList = Collections.emptyList();
		// unmodifedList.add(11);//报错

		// 创建一个只有一个元素，且不可改变的Set对象
		Set<String> unmodifiableSet = Collections.singleton("疯狂JAVA讲义");
		System.out.println(unmodifiableSet);

		// 创建一个普通的Map
		Map<String, Integer> mapp = new HashMap<>();
		mapp.put("语文", 100);
		mapp.put("数学", 200);
		mapp.put("生物", 300);
		mapp.put("化学", 400);

		// 返回普通Map对象的不可变版本
		Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(mapp);
		// unmodifiableMap.put("aa", 11);//报错 java.lang.UnsupportedOperationException
	}

}
