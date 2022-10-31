package javaThreadTest;

/*
 * 싱글 스레드 프로그램 예제
 */

public class T01ThreadTest {
	public static void main(String[] args) {
		for (int i = 0; i < 200; i++) {
			System.out.print("*");
		}
		System.out.println();
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
		}
	}
}
