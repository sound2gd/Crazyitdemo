package com.cris.chapter07;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 测试Random和ThreadLocalRandom
 * @author cris
 *
 */
public class RandomTest {

	public static void main(String[] args) {
//		Random ran = new Random();
		//下面是和线程绑定的Random
		ThreadLocalRandom ran = ThreadLocalRandom.current();
		//输出随机的布尔值
		System.out.println("ran.nextBoolean():"+ran.nextBoolean());
		//随机byte数组nextBytes
		byte[] bytes = new byte[16];
		ran.nextBytes(bytes);
		System.out.println("nextBytes:"+Arrays.toString(bytes));
		//0.0~1.0之间的随机数
		System.out.println("nextDouble:"+ran.nextDouble());
		//0.0~1.0之间的随机数
		System.out.println("nextFloat:"+ran.nextFloat());
		//Gaussian数
		System.out.println("nextGaussian:"+ran.nextGaussian());
		//整数
		System.out.println("nextInt:"+ran.nextInt());
		//0~30
		System.out.println("nextInt30:"+ran.nextInt(30));
		//long型
		System.out.println("nextLong:"+ran.nextLong());
	}
	
}
