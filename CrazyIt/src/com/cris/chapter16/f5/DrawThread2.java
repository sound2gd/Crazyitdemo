package com.cris.chapter16.f5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 取款线程 只要账户中还有钱就取款
 * 
 * @author cris
 *
 */
public class DrawThread2 extends Thread {

	private Account2 account;

	public DrawThread2(Account2 account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		while (true) {
			account.draw();
		}
	}

	public static void main(String[] args) {
		Account2 account = new Account2();
		account.setBalance(0);
		account.setHasMoney(false);

		new DrawThread2(account).start();
		new DepositThread2(account).start();
		new DepositThread2(account).start();
		new DepositThread2(account).start();
	}

}

/**
 * 取款线程
 * 
 * @author cris
 *
 */
class DepositThread2 extends Thread {

	private Account2 account;

	public DepositThread2(Account2 account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		while (true) {
			account.deposit(2);
		}
	}

}

/**
 * 模拟用户账户<br>
 * 该领域模型提供draw,deposit方法
 * 
 * @author cris
 *
 */
class Account2 {
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	// 账户中是否有钱
	private boolean hasMoney;
	// 余额
	private int balance;

	public boolean isHasMoney() {
		return hasMoney;
	}

	public void setHasMoney(boolean hasMoney) {
		this.hasMoney = hasMoney;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	/**
	 * 取钱方法
	 */
	public void draw() {
		lock.lock();
		try {

			if (hasMoney) {
				if (balance <= 0) {
					System.out.println("账户中没有钱了！正在等待存钱...");
					cond.await();
				} else {
					while (balance > 0) {
						balance--;
						System.out.println("账户中有钱，取款线程正在取钱...取出金额 1,账户余额= " + balance);
					}
					hasMoney = false;
					cond.signalAll();
					;// 通知其他线程存钱
				}
			} else {
				// 没钱则等待
				System.out.println("账户中没有钱了！正在等待存钱...");
				cond.await();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void deposit(int amount) {
		lock.lock();
		try {
			if (hasMoney) {
				// 如果有钱，则说明有人存钱进去了，存钱方法阻塞
				cond.await();
			} else {
				// 没钱了，存钱去
				System.out.println(Thread.currentThread().getName() + "线程存了" + amount);
				balance += amount;
				hasMoney = true;
				cond.signalAll();
				Thread.sleep(2000);
			}
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
	}

}
