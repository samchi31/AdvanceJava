package javaThreadTest;

/*
 * 멀티스레드 프로그램 생성 예제
 */

public class T02ThreadTest {
	public static void main(String[] args) {

		// 방법1 : Thread 클래스를 상속한 class의 인스턴스를 생성한 후 이 인스턴스의 start() 메서드를 호출한다
		// Thread 클래스를 상속하면 다른 클래스를 상속 못함
		Thread th1 = new Mythread1();
		th1.start();
		
		// 방법2 : Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		// 이 인스턴스를 Thread 객체의 인스턴스를 생성할 때 생성자의 파라미터로 넘겨준다
		// 이렇게 생성된 인스턴스의 start() 메서드를 호출한다
		// ** 다중 상속 안되는 단점을 보완
		Thread th2 = new Thread(new MyThread2());
		th2.start();
		
		// 방법3 : 익명클래스 이용하는 방법
		// 		   Runnable 인터페이스를 구현한 익명클래스를 Thread 인스턴스를  생성할 때 매개변수로 넘겨준다
		Thread th3 = new Thread(new Runnable()  {

			@Override
			public void run() {
				for (int i = 0; i < 200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		th3.start();
		
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		}).start();
	}
}

class Mythread1 extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 200; i++) {
			System.out.print("*");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}