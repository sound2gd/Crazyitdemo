package com.cris.chapter08;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 往HashSet中添加了可变对象之后<br>
 * 后面对该对象的值的修改，可能会造成Set集合中的元素重复
 * @author cris
 *
 */
public class HashSetTest {	
	
	public static void main(String[] args) {
		HashSet<Object> set =new HashSet<>();
		set.add(new R(-3));
		set.add(new R(4));
		set.add(new R(9));
		set.add(new R(-2));
		
		//操作第一个元素
		Iterator<Object> iterator = set.iterator();
		R first = (R) iterator.next();
		first.count=9;
		System.out.println(set);
		
		//删除元素时，仅删除了最后一个元素，第一个修改过的没删
		set.remove(new R(9));
		System.out.println(set);
		
		//后续的contains也出错
		System.out.println("是否包含9:"+set.contains(new R(9)));
	}

}

/**
 * 定义一个可变的类，重写了hashCode和equals方法
 * <br>hashCode是根据count
 * <br>equals也是根据count
 * @author cris
 *
 */
class R{
	int count;

	public R(int count) {
		super();
		this.count = count;
	}

	@Override
	public String toString() {
		return "R [count=" + count + "]";
	}

	@Override
	public int hashCode() {
		return count;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}else if(obj!=null && obj.getClass() == R.class){
			return ((R)obj).count == this.count;
		}
		return false;
	
	}
	
	
	
}