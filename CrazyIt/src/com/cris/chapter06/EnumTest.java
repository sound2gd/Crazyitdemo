package com.cris.chapter06;
/**
 * 测试Enum类
 * @author cris
 *
 */
public class EnumTest {

	public static void main(String[] args) {
		//通过values方法可以得到所有枚举的名称
		for (Season s:Season.values()){
			System.out.println(s.name());
//			System.out.println(s);
		}
		
		//通过compareTo可以比较两个枚举的先后顺序，之后返回正数，之前返回负数
		System.out.println(Season.Spring.compareTo(Season.Fall));
		
		
		for (Gender gender:Gender.values()){
			gender.info();
			System.out.println(gender.getName());
		}
		
		//调用实现了抽象方法的枚举类
		System.out.println(Operation.PLUS.eval(1, 3));
	}
}

enum Season{
	Spring,Summer,Fall,Winter
}

interface GenderDesc{
	void info();
}

enum Gender implements GenderDesc{
	MALE("男"){//后面可以带类体来自定义接口方法实现
		@Override
		public void info() {
			System.out.println("这是MALE的方法体");
		}
	},FEMALE("女");
	
	private String name;

	public String getName() {
		return name;
	}

	private Gender(String name) {
		this.name = name;
	}

	@Override
	public void info() {
		System.out.println("这是默认的方法体");
	}
	
}

//实现抽象方法的枚举类
enum Operation{
	PLUS {
		@Override
		public double eval(double x, double y) {
			return x+y;
		}
	},MINUS {
		@Override
		public double eval(double x, double y) {
			return x-y;
		}
	},MULTI {
		@Override
		public double eval(double x, double y) {
			return x*y;
		}
	},DIVIDE {
		@Override
		public double eval(double x, double y) {
			return x/y;
		}
	};
	
	public abstract double eval(double x,double y);
	
	
}