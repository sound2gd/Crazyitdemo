package com.cris.chapter08;

import java.util.TreeMap;

/**
 * 测试TreeMap的基本用法<br>
 * 和TreeSet类似,TreeMap默认使用的也是自然排序<br>
 * TreeMap中的元素也必须实现Comparable接口<br>
 * 底层采用红黑树数据结构,因为实现了排序所以性能比HashMap,HashTable要差
 * 
 * @author cris
 *
 */
public class TreeMapTest {

	public static void main(String[] args) {
		TreeMap<Object, Object> tm = new TreeMap<>();
		tm.put(new RTest(3), "轻量级JAVAEE时间");
		tm.put(new RTest(5), "轻量级JAVAEE3时间");
		tm.put(new RTest(4), "轻量级JAVAEE2时间");
		tm.put(new RTest(6), "轻量级JAVAEE4时间");

		// 按照自然排序法排序
		System.out.println(tm);

		// 放入TreeMap的也必须是同一类型的key
		// 以下报错
		// tm.put("哈哈哈", "asda");

		// 返回TreeMap的第一个Entry对象
		System.out.println(tm.firstEntry());

		// 返回TreeMap的最后一个Entry对象
		System.out.println(tm.lastEntry());

		// 返回比new R(4)大的key对应的Key
		System.out.println(tm.higherKey(new RTest(4)));

		// 返回比new R(4)小的key对应的entry
		System.out.println(tm.lowerEntry(new RTest(4)));

		// 返回比new R(4)大(包括)的key对应的Entry集合
		System.out.println(tm.tailMap(new RTest(4)));

		// 返回比new R(4)小(不包括)的key的集合
		System.out.println(tm.headMap(new RTest(4)));

		// 返回2(包括)~5(不包括)之间的集合
		System.out.println(tm.subMap(new RTest(3), new RTest(5)));

		// -----------自定义排序规则(逆序)----------
		TreeMap<M, Object> tm2 = new TreeMap<>((obj1, obj2) -> obj1.age > obj2.age ? -1 : obj1.age == obj2.age ? 0 : 1);
		tm2.put(new M(3), "轻量级JAVAEE时间");
		tm2.put(new M(5), "轻量级JAVAEE3时间");
		tm2.put(new M(4), "轻量级JAVAEE2时间");
		tm2.put(new M(6), "轻量级JAVAEE4时间");
		
		System.out.println(tm2);

	}

}

class RTest implements Comparable<RTest> {
	int count;

	@Override
	public int hashCode() {
		return this.count;
	}

	public RTest(int count) {
		super();
		this.count = count;
	}

	@Override
	public boolean equals(Object obj) {
		// 根据count比较大小
		if (this == obj) {
			return true;
		} else if (obj != null && obj.getClass() == RTest.class) {
			return ((RTest) obj).count == this.count;
		}
		return false;
	}

	@Override
	public String toString() {
		return "RTest [count=" + count + "]";
	}

	@Override
	public int compareTo(RTest o) {
		return this.count > o.count ? 1 : this.count == o.count ? 0 : -1;
	}

}
