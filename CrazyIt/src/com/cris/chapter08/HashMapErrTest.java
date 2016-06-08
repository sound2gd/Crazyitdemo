package com.cris.chapter08;

import java.util.HashMap;
import java.util.Map;

/**
 * 和HashSet类似，当使用可变类作为key时<br>
 * 如果修改了可变类的对象，那么HashMap中的元素将不能正确操作<br>
 * 所以尽量不要使用可变类的对象作为key,如果使用了可变类的对象作为key,也应尽量保证不在程序中修改可变类的对象
 * 
 * @author cris
 *
 */
public class HashMapErrTest {

	public static void main(String[] args) {
		Map<ATest, Object> map = new HashMap<>();
		ATest aTest = new ATest(112);
		map.put(new ATest(111), 1);
		map.put(aTest, 2);

		System.out.println(map);
		// 此时操作ATest(112)
		aTest.count = 111;
		System.out.println(map);

		// 删除ATest(111)的时候，理应删除俩，实际只删除1
		map.remove(new ATest(111));
		System.out.println(map);
	}
}
