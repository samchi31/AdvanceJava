package javaThreadTest;

import java.util.Random;

import javax.swing.JOptionPane;

public class T07RockScissorPaper {
	/*
	 * 입력여부를 확인하기 위한 변수선언 
	 * (모든 스레드에서 공통으로 사용할 변수)
	 */

	public static boolean inputCheck = false;

	public static void main(String[] args) {
		
		Thread th1 = new Game();
		th1.start();

		Thread th2 = new TimeOut();
		th2.start();
	}
}

class Game extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위바위보");
//		System.out.println("입력한 값은 : " + str);
		T06ThreadTest.inputCheck = true;
		
		int num = new Random().nextInt(3);
		String[] strArr = new String[] {"가위", "바위", "보"}; 
		
		System.out.println("컴퓨터 : " + strArr[num]);
		System.out.println("당신 : " + str);
		if(strArr[num].equals(str)) {
			System.out.println("비김");
		} else if((strArr[num].equals("가위")&&str.equals("바위")) ||
				(strArr[num].equals("바위")&&str.equals("보")) ||
				(strArr[num].equals("보")&&str.equals("가위")) ) {
			System.out.println("당신 이김");
		} else {
			System.out.println("컴퓨터 이김");
		}
	}
}

class TimeOut extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (T06ThreadTest.inputCheck) {
				return;
			}
			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 입력 없으면 프로그램 종료
		System.out.println("5초 지남 프로그램 종료");

		System.exit(0);
	}
}