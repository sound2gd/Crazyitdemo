package com.cris.chapter08;

import java.util.Hashtable;

/**
 * 测试HashTable<br>
 * HashTable，HashSet,HashMao判断key是否相等的标准都是:hashCode返回值相等和equals返回true<br>
 * HashTable，HashSet,HashMao判断value是否相等的标准是:equals返回true<br>
 * 所以重写hashCode的时候一定要重写equals方法，规则是hashCode相同则equals也返回true
 * 
 * @author cris
 *
 */
public class HashTableTest {

	public static void main(String[] args) {

		Hashtable<Object, Object> ht = new Hashtable<>();
		ht.put(new ATest(1999), new BTest());
		ht.put(new ATest(20000), "疯狂Java讲义");
		ht.put(new ATest(2222), "经典JEE讲义");
		ht.put(new ATest(87654), new BTest());

		System.out.println(ht);
		// 重写了hashCode和equals方法，count相等则true,不管是不是同一个对象
		System.out.println("包含key new ATest(2222):" + ht.containsKey(new ATest(2222)));
		// 以下可以看出，value相等的标准是equals返回true
		System.out.println("包含value 1:" + ht.containsValue(1));

		// 删除
		ht.remove(new ATest(2222));
		System.out.println(ht);
	}

}

/**
 * A类重写了hashCode和equals方法
 * 
 * @author cris
 *
 */
class ATest {

	int count;

	public ATest(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		return this.count;
	}

	@Override
	public boolean equals(Object obj) {
		// 根据count比较大小
		if (this == obj) {
			return true;
		} else if (obj != null && obj.getClass() == ATest.class) {
			return ((ATest) obj).count == this.count;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ATest [count=" + count + "]@" + this.hashCode();
	}
}

/**
 * B类重写equals方法
 * 
 * @author cris
 *
 */
class BTest {

	@Override
	public boolean equals(Object obj) {
		return true;
	}

	@Override
	public String toString() {
		return "BTest @" + this.hashCode();
	}

}