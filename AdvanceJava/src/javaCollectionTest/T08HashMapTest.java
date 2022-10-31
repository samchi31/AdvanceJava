package javaCollectionTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T08HashMapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();

		// 자료추가 => put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		System.out.println(map);

		// 자료수정 => 데이터를 저장할 때 key값이 같으면 나중에 입력한 값이 저장된다
		// put(수정할 key값, 새로운 value 값)
		map.put("addr", "서울");
		System.out.println(map);

		// 자료삭제 => remove(삭제할key값)
		map.remove("name");
		System.out.println(map);

		// 자료읽기 => get(key값)
		System.out.println(map.get("addr"));
		System.out.println("===================================");
		
		// key 값들을 읽어와 자료를 출력하는 방법

		// 방법1 => keySet() 메서드 이용하기
		// Map의 key값들만 읽어와 Set타입의 객체로 반환한다
		Set<String> keySet = map.keySet();

		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("-----------------------------------");
		
		// 방법2 => Set타입의 객체를 'foreach'로 처리하는 방법
		for (String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("-----------------------------------");
		
		// 방법3 => value값만 읽어와 출력하기
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("-----------------------------------");
		
		// 방법4 => Map관련 클래스에는 Map.Entry타입의 내부 클래스가 만들어져 있다
		// Map.Entry 클래스는 key와 value라는 멤버변수로 구성되어 있다
		// Map에서는 이 Map.Entry 타입의 객체들을 Set타입의 객체로 리턴해주는 메서드가 있다
		Set<Map.Entry<String, String>> entrySet = map.entrySet();

		// 가져온 Entry객체들을 순서대로 처리하기 위해 Iterator객체로 반환
		Iterator<Map.Entry<String, String>> entryIt = entrySet.iterator();
		while (entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
}
