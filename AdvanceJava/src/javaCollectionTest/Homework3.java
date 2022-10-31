package javaCollectionTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Homework3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Map<Integer, String> roomMap = new HashMap<Integer, String>();

		System.out.println("******************************");
		System.out.println("호텔문을 열었습니다");
		System.out.println("******************************");
		while (true) {
			System.out.println("---------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
			System.out.print("메뉴선택 >");
			int num = Integer.parseInt(scanner.next());
			switch (num) {
			case 1:
				System.out.println("체크인");
				System.out.print("방번호 >");
				int roomNum = Integer.parseInt(scanner.next());

				if (roomMap.get(roomNum) != null) {
					System.out.println("이미 방이 예약됨");
					break;
				}

				System.out.println("이름입력 >");
				String name = scanner.next();
				roomMap.put(roomNum, name);

				System.out.println("체크인 되었습니다");
				break;
			case 2:
				System.out.println("체크아웃");
				System.out.print("방번호 >");
				int rNum = Integer.parseInt(scanner.next());

				if (roomMap.remove(rNum) == null) {
					System.out.println("그 방은 비어있습니다");
				} else {
					System.out.println("체크아웃 되었습니다.");
				}

				break;

			case 3:
				Set<Integer> numSet = roomMap.keySet();
				Iterator<Integer> iterator = numSet.iterator();
				while (iterator.hasNext()) {
					int n = iterator.next();
					String h = roomMap.get(n);
					System.out.println("방번호:" + n + ", 투숙객:" + h);
				}
				break;
			case 4:
				System.out.println("종료");
				return;
			}

		}
	}
}
