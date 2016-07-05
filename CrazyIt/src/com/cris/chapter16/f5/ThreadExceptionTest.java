package com.cris.chapter16.f5;

class MyHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t.getName()+"线程出现了异常,"+e);
	}
	
}

public class ThreadExceptionTest {

	public static void main(String[] args) {
		//设置主线程的异常处理器
		Thread.currentThread().setUncaughtExceptionHandler(new MyHandler());
		int a = 5/0;
		System.out.println("程序正常结束");
	}
}
