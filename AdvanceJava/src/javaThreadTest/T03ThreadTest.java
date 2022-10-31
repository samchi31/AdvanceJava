package javaThreadTest;

/*
 * 스레드의 수행시간 체크해보기
 */
public class T03ThreadTest {
	public static void main(String[] args) {
		Thread th = new Thread(new MyRunnable());
		
		// UTC(Universal Time Coordinated  협정 세계 표준시)를 사용하여 1970/1/1 0시0분0초 기준으로 경과한 시간을 ms 단위로 반환
		long startTime = System.currentTimeMillis();
		th.start();
		try {
			//현재 실행중인 스레드에서 작업중인 스레드가 종료될 때 까지 기다린다
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(endTime - startTime);
	}
}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		// 1~100000000 까지의 합계
		long sum = 0;
		for (int i = 1; i <= 1000000000; i++) {
			sum += 1;
		}
		System.out.println(sum);
	}

}