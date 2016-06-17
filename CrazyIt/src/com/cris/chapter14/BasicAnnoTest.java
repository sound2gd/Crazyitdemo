package com.cris.chapter14;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本Annotation测试
 * 
 * @author cris
 *
 */
public class BasicAnnoTest {
	// 使用@Deprecated标记的类或者方法会提示已过时
	C c = new C();

	public static void main(String[] args) {
		// 以下会引发堆污染
		List list = new ArrayList<Integer>();
		list.add(20);// 引发unchecked异常

		List<String> ls = list;
		//引发运行时异常
		System.out.println(ls.get(0));
	}

}

class A {
	void override() {
		System.out.println("父类的方法");
	}
}

class B extends A {
	// @Override会检查父类的同名方法是否存在
	// 以下注解会引起编译异常
	// @Override
	void over() {
		System.out.println("子类的方法");
	}
}

@Deprecated
class C {

}

@FunctionalInterface
interface f{
	//使用@FunctionInterface定义的接口只能有一个抽象方法
	//定义一个以上的抽象方法将发生编译错误
	void action();
//	void aaa();
}