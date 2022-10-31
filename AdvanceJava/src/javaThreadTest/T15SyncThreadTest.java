package javaThreadTest;

public class T15SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();

		WorkerThread th1 = new WorkerThread("1", sObj);
		WorkerThread th2 = new WorkerThread("2", sObj);
		th1.start();
		th2.start();
	}
}

//공통으로 사용할 객체
class ShareObject {
	private int sum = 0;

	// 동기화 하는 방법 1 : 메서드 자체에 동기화 처리
	/* synchronized */public void add() {

		// 동기화 하는 방법2 : 동기화 블럭으로 설정 1
		// mutex: mutual exclusion object(상호배제 : 동시접근차단)
		//synchronized (/* mutex */ this) {

			for (int i = 0; i < 1000000000; i++) {
			}
			int n = sum;
			n += 10;
			sum = n;
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);

		//}
	}
}

//작업 수행하는 스레드
class WorkerThread extends Thread {
	private ShareObject sObj;

	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			// 동기화 하는 방법2 : 동기화 블럭으로 설정 2
			synchronized (sObj) {
				sObj.add();
			}
		}
	}
}