package javaCollectionTest;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Homework2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Homework2 homework2 = new Homework2();
		
		while (true) {
			System.out.println("Lotto 프로그램");
			System.out.println("-----------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 종료");
			System.out.print("메뉴선택 >");
			int num = Integer.parseInt(scanner.nextLine());
			switch (num) {
			case 1:
				System.out.println("Lotto 구입 시작");
				System.out.println("(1000에 로또번호 하나입니다)");
				System.out.print("금액 입력 : ");
				int money = Integer.parseInt(scanner.nextLine());
				int remain = money;
				System.out.println("행운의 로또번호는 아래와 같습니다.");

				int i = 1;
				while (true) {
					if (1000 * i > money) {
						break;
					}
					System.out.printf("로또 번호 %d : ", i);
					homework2.printNum();
					remain -= 1000;
					i++;
				}
				System.out.println("받은 돈 : " + money + ", 거스름돈 : " + remain);
				break;
			case 2:
				System.out.println("감사합니다.");
				return;
			}
			System.out.println("====================================");
		}
	}

	public void printNum() {
		Random rand = new Random();
		Set<Integer> setRnd = new HashSet<Integer>();
		while (setRnd.size() < 6) {
			setRnd.add(rand.nextInt(45)+1);
		}
		System.out.println(setRnd);
	}
}
