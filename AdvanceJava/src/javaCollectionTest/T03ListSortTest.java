package javaCollectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T03ListSortTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println(list);
		
		/*
		 * 정렬은 Collections.sort() 메서드를 이용하여 정렬
		 * 정렬은 기본적으로 '오름차순 정렬' 수행
		 * 정렬방식을 변경하려면 정렬방식을 결정하는 객체를 만들어서 Collections.sort() 메서드의 인수값으로 넘겨주면 된다
		 */
		
		Collections.sort(list);	//오름차순
		System.out.println(list);
		
		Collections.shuffle(list);
		System.out.println(list);

		// 정렬방식을 결정하는 객체를 이용하여 정렬하기
		Collections.sort(list, new Desc());
		System.out.println(list);
	}
}

/*
 * 정렬방식을 결정하는 class는 Comparator라는 인터페이스를 구현해야한다
 * 이 Comparator 인터페이스의 compare()라는 메서드를 재정의 하여 구현하면 된다
 */
class Desc implements Comparator<String> {
	
	/*
	 * compare() 메서드의 반환값을 결정하는 방법
	 * - 오름차순 정렬일 경우
	 * 		=> 앞의 값이 크면 양수, 같으면 0, 작으면 음수를 반환하도록 한다.
	 */

	@Override
	public int compare(String arg0, String arg1) {
		return arg0.compareTo(arg1) * -1;	// 내림차순
	}

	
	
}