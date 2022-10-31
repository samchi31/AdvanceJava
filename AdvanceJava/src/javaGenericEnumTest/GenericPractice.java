package javaGenericEnumTest;

import java.util.ArrayList;
import java.util.List;

public class GenericPractice {
	public static void main(String[] args) {
		Test<Integer, String> p1 = new Test<Integer, String>(1, "홍길동");
		Test<Integer, String> p2 = new Test<Integer, String>(1, "홍길동");
		
		// 일반 메소드 ( 클래스 선언 시 사용된 제너릭 타입을 그대로 매개변수 타입으로)
		p1.displayAll1(1, "Test");
		// 제네릭 메소드 (매개변수 타입이 자유로움)
		p2.displayAll("asd", 1);	
		p2.displayAll(0.0001, -123);
		
		//음식 목록
		TestList<Food> lst = new TestList();
		lst.add(new Food("짜장면", 5000));
		lst.add(new Food("짬뽕", 7000));
		lst.add(new Food("탕수육", 10000));		
		
		//					TestList<Food>
		//public void print(TestList<?> lst) {
		p1.print(lst);
	}
}
class Test<K, V> {
	private K key;
	private V value;

	public Test(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	public void displayAll1(K key,V val) {
		System.out.println(key + ":" + val);
	}
	
	//제네릭 메소드(generic method)
	// 키와 값을 모두 출력
	public <K, V> void displayAll(K key, V val) {
		System.out.println(key + ":" + val);
	}
	
	//일반 메소드
	// 와일드 카드는 리턴 타입을 변경할 수 없는 거 같다
	public void print(TestList<?> lst) {
		for (Object obj : lst.getList()) {
			System.out.println(obj);
		}
	}
	
	/*
	 제네릭 메소드(generic method)
	제네릭 메소드란 메소드의 선언부에 타입 변수를 사용한 메소드를 의미합니다.
	이때 타입 변수의 선언은 메소드 선언부에서 반환 타입 바로 앞에 위치합니다.
	 */
	// 제너릭 메소드는 리턴 타입을 변경할 수 있다
	public <T> T print1(TestList<T> lst) {
		for (Object obj : lst.getList()) {
			System.out.println(obj);
		};
		return null;
	}
	
	
	public void testMethod(List<? extends Drink> lstDrink) {
		Drink drink1 = new Coffee("밀크",5000);
		//lstDrink.add(drink1);
		// 와일드 카드 일 때는 타입을 확신할 수 없어서 불가 <= ?
	}
}

class TestList<T> {
	private List<T> list = new ArrayList<T>();

	public List<T> getList() {
		return list;
	}

	public void add(T item) {
		list.add(item);
	}
}