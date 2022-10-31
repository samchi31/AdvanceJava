package javaThreadTest;

public class T16SyncAccountTest {
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.deposit(10000);
		
		Thread th1 = new BankThread(sAcc);
		Thread th2 = new BankThread(sAcc);
		
		th1.start();
		th2.start();
	}
}

// 은행의 입출금을 관리하는 클래스 (공유객체)
class SyncAccount {
	private int balance;

	synchronized public int getBalance() {
		return balance;
	}

	// 입금처리를 수행하는 메서드
	public void deposit(int money) {
		balance += money;
	}

	// 출금 메서드
	// 동기화 영역에서 호출하는 메서드도 동기화 처리
	synchronized public boolean withdraw(int money) {
		if (balance >= money) {
			for (int i = 0; i <= 1000000000; i++) {
			}
			balance -= money;

			System.out.println("메서드 안에서 balance = " + getBalance());

			return true;
		} else {
			return false;
		}
	}
}

// 은행 업무를 처리하는 스레드
class BankThread extends Thread {
	private SyncAccount sAcc;

	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}

	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000);
		System.out.println("스레드 안에서 result = " + result + ", balance = " + sAcc.getBalance());
	}
}