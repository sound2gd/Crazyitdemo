package com.cris.chapter16.f5;

/**
 * 取款线程 只要账户中还有钱就取款
 * 
 * @author cris
 *
 */
public class DrawThread extends Thread {

	private Account account;

	public DrawThread(Account account) {
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
		Account account = new Account();
		account.setBalance(0);
		account.setHasMoney(false);
		
		new DrawThread(account).start();
		new DepositThread(account).start();
		new DepositThread(account).start();
		new DepositThread(account).start();
	}

}

/**
 * 取款线程
 * 
 * @author cris
 *
 */
class DepositThread extends Thread {

	private Account account;

	public DepositThread(Account account) {
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
class Account {
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
	public synchronized void draw() {
		if (hasMoney) {
			if (balance <= 0) {
				try {
					System.out.println("账户中没有钱了！正在等待存钱...");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				while (balance > 0) {
					balance--;
					System.out.println("账户中有钱，取款线程正在取钱...取出金额 1,账户余额= " + balance);
				}
				hasMoney = false;
				notifyAll();// 通知其他线程存钱
			}
		} else {
			// 没钱则等待
			try {
				System.out.println("账户中没有钱了！正在等待存钱...");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void deposit(int amount) {
		if (hasMoney) {
			// 如果有钱，则说明有人存钱进去了，存钱方法阻塞
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			// 没钱了，存钱去
			System.out.println(Thread.currentThread().getName()+"线程存了"+amount);
			balance += amount;
			hasMoney = true;
			notifyAll();

		}
	}

}
