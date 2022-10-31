package javaThreadTest;

public class T08ThreadPriorityTest {
	public static void main(String[] args) {
		System.out.println(Thread.MAX_PRIORITY);
		System.out.println(Thread.MIN_PRIORITY);
		System.out.println(Thread.NORM_PRIORITY);
		
		Thread[] ths = new Thread[] {
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest2()
		};
		
		for(int i=0; i< ths.length; i++) {
			if(i==5) {
				ths[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				ths[i].setPriority(Thread.MIN_PRIORITY);
			}
		}
		
		for (Thread th : ths) {
			System.out.println(th.getName()+ " : "+ th.getPriority());
		}
		
		for (Thread thread : ths) {
			thread.start();
		}
		for (Thread thread : ths) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class ThreadTest1 extends Thread{
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(ch);
			
			for(long i=1;i<=1000000000;i++) {}
		}
	}
}

class ThreadTest2 extends Thread{
	@Override
	public void run() {
		for(char ch = 'a'; ch <= 'z'; ch++) {
			System.out.println(ch);
			
			for(long i=1;i<=1000000000;i++) {}
		}
	}
}
