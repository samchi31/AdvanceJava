package javaThreadTest;

import javax.swing.JOptionPane;

public class T05ThreadTest {
	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		System.out.println("입력한 값은 : " + str);

		for (int i = 10; i >= 1; i--) {
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
