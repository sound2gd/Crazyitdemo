package com.cris.chapter07;

import java.io.InputStream;

/**
 * Runtime类测试 <br/>
 * Runtime类代表的是Java程序的运行时环境<br/>
 * 可以访问得到JVM的相关信息，如处理器数量，内存信息等
 * 
 * @author cris
 *
 */
public class RuntimeTest {

	public static void main(String[] args) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("处理器数量:" + runtime.availableProcessors());
		System.out.println("空余内存:" + runtime.freeMemory() / 1000 / 1000 + "M");
		System.out.println("总内存数:" + runtime.totalMemory() / 1000 / 1000 + "M");
		System.out.println("可用最大内存数:" + runtime.maxMemory() / 1000 / 1000 + "M");

		// 通过runtime.exec可以执行操作系统的命令
		Process exec = runtime.exec("ls -ltr");
		//得到执行的结果,并输出到控制台
		InputStream inputStream = exec.getInputStream();
		byte[] buffer = new byte[1024];
		int length = 0;
		while((length=inputStream.read(buffer)) != -1){
			System.out.println(new String(buffer,0,length));
		}
	}

}
