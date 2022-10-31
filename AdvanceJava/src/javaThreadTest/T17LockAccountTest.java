package javaThreadTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T17LockAccountTest {
	/*
	 * 락 기능을 제공하는 클래스
	 * 
	 * ReentrantLock : Read , Write 구분없이 사용하기 위한 락 클래스. synchronized를 이용한 동기화 처리보다
	 * 부가적인 기능이 제공된다 ex ) Fairness 설정 -> 가장 오래 기다린 스레드가 가장 먼저 락을 획득하게 됨
	 * 
	 * ReentrantReadWriteLock : Read, Write 락을 구분하여 사용 가능. 여러 스레드가 동시에 read 작업은
	 * 가능하지만, write 작업은 하나의 스레드만 가능 => write 보다 read 위주의 작업이 많이 발생하는 경우에 사용하면 유리
	 */
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock(true);
		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000);
		
		Thread th1 = new BankThread2(lAcc);
		Thread th2 = new BankThread2(lAcc);
		
		th1.start();
		th2.start();
	}
}

class LockAccount {
	private int balance;

	// lock 객체 생성 => 되도록 private final
	private final Lock lock;

	public LockAccount(Lock lock) {
		this.lock = lock;
	}

	public int getBalance() {
		return balance;
	}

	public void deposit(int money) {
		// Lock 객체의 lock()메서드가 동기화 시작, unlock() 메서드가 동기화 끝
		// lock() 메서드로 동기화를 설정한 곳에서는 반드시 unlock() 메서드로 해제해 주어야 한다

		lock.lock(); // 락 획득하기 전까지 Block 상태
		balance += money;
		lock.unlock();
	}

	public boolean withdraw(int money) {
		boolean chk = false;

		try {
			lock.lock();
			
			if (balance >= money) {
				for (int i = 0; i <= 1000000000; i++) {
				}
				balance -= money;

				System.out.println("메서드 안에서 balance = " + getBalance());

				chk = true;
			} 
			
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
		return chk;
	}
}

class BankThread2 extends Thread{
	private LockAccount lAcc;
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println("스레드 안에서 result = " + result + ", balance = " + lAcc.getBalance());
	}
}