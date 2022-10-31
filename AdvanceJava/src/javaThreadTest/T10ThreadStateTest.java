package javaThreadTest;

public class T10ThreadStateTest {
	
	/*
	 * Thread의 상태
	 * 
	 * - NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
	 * - RUNNABLE : 실행 중 또는 실행 가능한 상태
	 * - BLOCKED : 동기화 블럭에 이해서 일시정지된 상태 (Lock이 풀릴 때까지 기다리는 상태)
	 * - WAITING, TIMED_WATING : 스레드이 작업이 종료되지는 않았지만 실행 가능하지는 않은 일시정지 상태 (TIMED_WAITING 은 시간이 지정된 경우)
	 * - TERMINATED : 스레드의 작업이 종료된 상태
	 */
	
	public static void main(String[] args) {
		Thread target = new TargetThread();
		StatePrintThread spt = new StatePrintThread(target);
		spt.start();
	}
}
class TargetThread extends Thread {
	@Override
	public void run() {
		for(long i=1; i<=1000000000L;i++) {}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		for(long i=1; i<=1000000000L;i++) {}
	}
}

class StatePrintThread extends Thread{
	private Thread targetThread; //모니터링 대상 스레드 객체
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}

	@Override
	public void run() {
		while(true) {
			Thread.State state = this.targetThread.getState();
			System.out.println("타겟 스레드의 상태 : " + state);
			
			if(state == Thread.State.NEW) {
				targetThread.start();
			}
			
			if(state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}