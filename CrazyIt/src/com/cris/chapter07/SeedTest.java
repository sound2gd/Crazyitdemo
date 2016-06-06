package com.cris.chapter07;

import java.util.Random;

/**
 * 测试Random类的seed<br>
 * 如果random类的seed相同，代码也相同，<br>
 * 得到的是同一个随机数
 * 
 * @author cris
 *
 */
public class SeedTest {
	public static void main(String[] args) {
		//以下随机数得到的结果相同
		//所以Random是一个伪随机数
		Random rand1 = new Random(50);
		System.out.println("第一个种子为５０的对象");
		System.out.println("nextBoolean:"+rand1.nextBoolean());
		System.out.println("nextDouble:"+rand1.nextDouble());
		System.out.println("nextFloat:"+rand1.nextFloat());
		System.out.println("nextInt:"+rand1.nextInt());
		System.out.println("nextGaussian:"+rand1.nextGaussian());
		Random rand2 = new Random(50);
		System.out.println("第2个种子为５０的对象");
		System.out.println("nextBoolean:"+rand2.nextBoolean());
		System.out.println("nextDouble:"+rand2.nextDouble());
		System.out.println("nextFloat:"+rand2.nextFloat());
		System.out.println("nextInt:"+rand2.nextInt());
		System.out.println("nextGaussian:"+rand2.nextGaussian());
		Random rand3 = new Random(50);
		System.out.println("第3个种子为５０的对象");
		System.out.println("nextBoolean:"+rand3.nextBoolean());
		System.out.println("nextDouble:"+rand3.nextDouble());
		System.out.println("nextFloat:"+rand3.nextFloat());
		System.out.println("nextInt:"+rand3.nextInt());
		System.out.println("nextGaussian:"+rand3.nextGaussian());
		
		//=========================================================
		//不放种子，使用默认的种子,默认的种子是System.nanoTime()
		Random rand4 = new Random();
		System.out.println("第2个种子为５０的对象");
		System.out.println("nextBoolean:"+rand4.nextBoolean());
		System.out.println("nextDouble:"+rand4.nextDouble());
		System.out.println("nextFloat:"+rand4.nextFloat());
		System.out.println("nextInt:"+rand4.nextInt());
		System.out.println("nextGaussian:"+rand4.nextGaussian());
		Random rand5 = new Random();
		System.out.println("第3个种子为５０的对象");
		System.out.println("nextBoolean:"+rand5.nextBoolean());
		System.out.println("nextDouble:"+rand5.nextDouble());
		System.out.println("nextFloat:"+rand5.nextFloat());
		System.out.println("nextInt:"+rand5.nextInt());
		System.out.println("nextGaussian:"+rand5.nextGaussian());
	}
}
