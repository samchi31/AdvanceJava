package javaCollectionTest;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class T01ArrayListTest {
	public static void main(String[] args) {
		// Default_Capacity = 10;
		List list1 = new ArrayList();

		list1.add("aaa");
		list1.add("bbb");
		list1.add('d');
		list1.add(111);
		list1.add(true);
		list1.add(12.34);

		System.out.println(list1.size());
		System.out.println(list1);

		System.out.println(list1.get(0));

		list1.add(0, "zzz");
		System.out.println(list1);

		String temp = (String) list1.set(0, "YYY");
		System.out.println(temp);
		System.out.println(list1);

		list1.remove(0);
		System.out.println(list1);

		list1.remove("bbb");
		System.out.println(list1);
		
		list1.remove(new Integer("111"));
		System.out.println("list1");

		System.out.println("================================================");

		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");

		for (int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i));
		}

		System.out.println("----------------------------------------------");
		
		// contain(비교객체); => 리스트에 '비교객체'가 있으면 true 없으면 false
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));

		// indexOf(비교객체); => 리스트에서 '비교객체'를 찾아 '비교객체'가 있는 index값을 반환 없으면 -1 반환
		System.out.println(list2.indexOf("DDD"));
		System.out.println(list2.indexOf("ZZZ"));

		System.out.println("----------------------------------------------");

//		// list 삭제? -> 안됨
//		for (int i = 0; i < list2.size(); i++) {
//			list2.remove(list2.get(i));
//		}
		
		// iterator를 이용한 삭제방법
		Iterator<String> iter = list2.iterator();
		while(iter.hasNext()) {
			iter.next();
			iter.remove();
		}
		System.out.println(list2);
	}
}
