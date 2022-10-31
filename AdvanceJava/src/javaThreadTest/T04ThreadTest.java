package javaThreadTest;

public class T04ThreadTest {
	public static void main(String[] args) {
		/*
		 * 1~20억까지의 합계를 구하는 데 걸린 시간 체크하기
		 * 
		 * 전체 합계를 구하는 작업을 단독스레드로 하는 경우와 여러개의 스레드로 분할 처리하는 경우 시간 차이 확인
		 */

		// 단독
		Thread th = new SumThread(1, 2000000000L);

		long startTime = System.currentTimeMillis();

		th.start();

		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();

		System.out.println("단독 : " + (endTime - startTime));

		// 여러개
		SumThread[] sumThs = new SumThread[] { 
				new SumThread(1L, 500000000L), 
				new SumThread(500000000L, 1000000000L),
				new SumThread(1000000000L, 1500000000L), 
				new SumThread(1500000000L, 2000000000L) };

		startTime = System.currentTimeMillis();
		
		for (Thread sumThread : sumThs) {
			sumThread.start();
		}


		for (Thread sumThread : sumThs) {
			try {
				sumThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		endTime = System.currentTimeMillis();
		System.out.println("협력처리 : " + (endTime - startTime));
	}
}

class SumThread extends Thread {
	private long min, max;

	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public void run() {
		long sum = 0;
		for (long i = min; i <= max; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}