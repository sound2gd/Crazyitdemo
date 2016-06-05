package com.cris.chapter07;
/**
 * 测试Math类<br>
 * 下面的测试基本涵盖了Math类的所有方法
 * @author cris
 *
 */
public class MathTest {
	public static void main(String[] args) {
		/*下面是三角运算*/
		//弧度转化为角度
		System.out.println("Math.toDegrees(1.57):"+Math.toDegrees(1.57));
		//角度转化成弧度
		System.out.println("Math.toRadians(90):"+Math.toRadians(90));
		//计算反余弦
		System.out.println("Math.acos(1.2):"+Math.acos(1.2));
		//计算反正弦
		System.out.println("Math.asin(0.8):"+Math.asin(0.8));
		//计算余弦
		System.out.println("cos(60)="+Math.cos(Math.PI/3));
		//计算正弦
		System.out.println("sin(30)="+Math.sin(Math.PI/6));
		//计算正切
		System.out.println("tan45 = "+Math.tan(Math.PI/4));
		//计算双曲余弦，正弦，正切
		System.out.println("cosh30="+Math.cosh(Math.PI/6));
		System.out.println("sinh30="+Math.sinh(Math.PI/6));
		System.out.println("tanh30="+Math.tanh(Math.PI/6));
		//直角坐标转化成极坐标
		System.out.println("0.1,0.2 = "+Math.atan2(0.1, 0.2));
		
		/*--------取整运算------*/
		//取整，小于目标数的最大整数
		System.out.println("不超过4.7的最大整数"+Math.floor(4.7));
		//取整，大于目标数的最大整数
		System.out.println("超过4.7的最大整数"+Math.ceil(4.7));
		//四舍五入取整
		System.out.println("4.7四舍五入"+Math.round(4.7));
		
		/**乘方，开方等运算**/
		//平方根
		System.out.println("根号9="+Math.sqrt(9));
		//立方根
		System.out.println("立方根号27="+Math.cbrt(27));
		//返回欧拉幂数的n次幂
		System.out.println("e3="+Math.exp(3));
		//余数运算
		System.out.println("remainder:"+Math.IEEEremainder(5, 2));
		//乘方
		System.out.println("3的立方"+Math.pow(3, 3));
		//计算自然对数
		System.out.println(Math.log(Math.E));
		//计算底数为１０的对数
		System.out.println(Math.log10(1000));
		//计算参数与１之和的自然对数
		System.out.println(Math.log1p(9));
		
		/*----------符号相关的运算------------*/
		//绝对值
		System.out.println(Math.abs(-6));
		//符号函数 如果参数大于０，返回１.0,参数小于0，返回-1.0，参数＝０．返回０
		System.out.println(Math.signum(3));
		System.out.println(Math.signum(-3));
		System.out.println(Math.signum(0));
		//最大值
		System.out.println(Math.max(4, 88));
		//最小值
		System.out.println(Math.min(3, 5));
		//伪随机数，大于０小于１
		System.out.println(Math.random());
		//比第一个数略大的浮点数
		System.out.println(Math.nextAfter(1.1, 1.2));
		//比目标数略大的数
		System.out.println(Math.nextUp(3.4));
	}
}
