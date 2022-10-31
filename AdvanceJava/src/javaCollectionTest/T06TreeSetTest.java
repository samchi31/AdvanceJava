package javaCollectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class T06TreeSetTest {
	public static void main(String[] args) {
		// TreeSet은 자동 정렬 기능이 들어가 있다.

		TreeSet<String> ts = new TreeSet<>();
		List<String> abcList = new ArrayList<>();

		// 영어 대분자를 문자열로 변환하여 List에 저장
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			String temp = String.valueOf(ch);
			abcList.add(temp);
		}

		Collections.shuffle(abcList);
		System.out.println(abcList);

		for (String str : abcList) {
			ts.add(str);
		}

		System.out.println(ts);

		// TreeSet에 저장된 자료 중 특정한 자료보다 작은 자료를 찾아서 SortedSet으로 반환
		// => headSet(기준값) : '기준값'은 포함되지 않음
		// => headSet(기준값, 논리값) : '논리값'이 true면 '기준값'을 포함
		SortedSet<String> ss1 = ts.headSet("K");
		System.out.println(ss1); 					// 기준값 미포함
		System.out.println(ts.headSet("K", true)); 	// 기준값 포함

		// TreeSet에 저장된 자료 중 특정한 자료보다 큰 자료를 찾아서 SortedSet으로 반환
		// => tailSet(기준값) : '기준값'을 포함
		// => tailSet(기준값, 논리값) : '논리값'이 false면 '기준값'을 미포함
		SortedSet<String> ss2 = ts.tailSet("K");
		System.out.println(ss2); 					// 기준값 미포함
		System.out.println(ts.tailSet("K", false)); 	// 기준값 포함
		
		// subSet(기준값1, 기준값2) : '기준값1 ~ 기준값2' 사이의 값을 가져옴 (기준값1 포함, 기준값2 미포함)
		// subSet(기준값1, 논리값1, 기준값2, 논리값2) : 논리값으로 포함여부 결정
		System.out.println(ts.subSet("K", "N"));
		System.out.println(ts.subSet("K", true, "N", true));
		System.out.println(ts.subSet("K", false, "N", false));
		System.out.println(ts.subSet("K", false, "N", true));
		
	}
}
