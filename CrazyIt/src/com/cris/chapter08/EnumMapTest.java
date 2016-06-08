package com.cris.chapter08;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * 测试EnumMap<br>
 * EnumMap是Map中性能最好的
 * @author cris
 *
 */
public class EnumMapTest {

	public static void main(String[] args) {
		//EnumMap中的元素会按照key在Enum中的顺序排列
		EnumMap<Season, Object> em =new EnumMap<>(Season.class);
		em.put(Season.WINTER, "冬天");
		em.put(Season.FALL, "秋天");
		
		System.out.println(em);
		System.out.println(em.keySet());
	}
	
}
