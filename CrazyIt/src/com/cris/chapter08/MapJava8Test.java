package com.cris.chapter08;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试Java8中Map新增的方法<br>
 * 
 * @author cris
 *
 */
public class MapJava8Test {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("疯狂Java讲义", 79);
		map.put("疯狂Android讲义", 109);
		map.put("疯狂IOS讲义", 19);
		map.put("疯狂AJAX讲义", 29);
		map.put("疯狂XML讲义", 39);
		map.put("疯狂JavaScript讲义", 99);
		//HashMap中允许存放null类型的键值对
		map.put(null, null);
		map.put(null, null);
		
		//尝试替换key为"疯狂BUG讲义"的值，但是原map中该key不存在
		//所以会替换失败
		map.replace("疯狂BUG讲义", 3333);
		System.out.println(map);
		
		//使用原来的value和传入参数计算出来的结果覆盖原有的value
		//第三个参数是一个函数，其方法的返回值是Map的Value类型或者Value类的子类型
		map.merge("疯狂AJAX讲义", 22, (oldVal,param)-> oldVal+param);
		System.out.println(map);
		
		//当key为"Java",对应的value为null(或者不存在)时，使用计算的结果作为新的value
		//第二个参数是一个函数，要求以key为参数,返回value类型的值
		map.computeIfAbsent("Java", key -> key.length());
		//添加了一组新的键值对 [Java=4]
		System.out.println(map);
		
		//当key为'Java'对应的value存在时，使用计算的结果作为新的value
		map.computeIfPresent("Java",(key,value) -> value*value);
		System.out.println(map);//Java=16
	}
	
}
