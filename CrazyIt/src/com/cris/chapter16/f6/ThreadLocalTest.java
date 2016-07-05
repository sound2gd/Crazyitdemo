package com.cris.chapter16.f6;

/**
 * 使用ThreadLocal来实现线程安全
 * 
 * @author cris
 *
 */
public class ThreadLocalTest {

	public static void main(String[] args) {
		//启动两个线程，两个线程共享同一个Account
		Account account = new Account("初始化名字");
		new MyTest(account, "线程甲").start();
		new MyTest(account, "线程乙").start();
	}

}

class Account {
	// 定义一个ThreadLocal类型的变量，该变量将是一个线程局部变量，每一个线程都会保留该变量的一个副本
	private ThreadLocal<String> name = new ThreadLocal<>();

	public Account(String name) {
		this.name.set(name);
		// 访问当前副本值
		System.out.println("---->" + this.name.get());
	}

	public String getName() {
		return this.name.get();
	}

	public void setName(String name) {
		this.name.set(name);;
	}
}

class MyTest extends Thread{

	private Account account;
	
	public MyTest(Account account,String name) {
		super(name);
		this.account = account;
	}

	@Override
	public void run() {
		//循环10次
		for (int i = 0; i < 10; i++) {
			if(i==6){
				account.setName(getName());
			}
			//输出同一个账户的账户名和循环变量
			System.out.println(account.getName()+"账户i的值:"+i);
		}
	}
	
}
