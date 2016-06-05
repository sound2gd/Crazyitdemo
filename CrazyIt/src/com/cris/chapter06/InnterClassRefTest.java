package com.cris.chapter06;
/**
 * 测试非静态内部类引用
 * 非静态内部类对象保存在外部类对象的一个引用
 * @author cris
 *
 */
public class InnterClassRefTest {
	
	public static void main(String[] args) {
		OutterClass out  = new OutterClass();
		//内部类对象必须寄生在外部类对象里
		OutterClass.InnterClass in = out.new InnterClass();
		in.test();
	}
}

class OutterClass{
	private String name = "外部类";
	class InnterClass{
		private String name="内部类";
		
		void test(){
			//内部类可以访问外部类的私有成员变量
			System.out.println("外部类的name变量是:"+OutterClass.this.name);
			//访问自己的成员变量
			System.out.println("内部类的name变量是:"+this.name);
		}
	}
}