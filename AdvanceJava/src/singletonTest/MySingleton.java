package singletonTest;

public class MySingleton {
/*
 * 싱글턴 패턴 => 객체(인스턴스)를 한 개만 만들어지도록하는 프로그래밍 방법
 * 
 * - Singleton 클래스를 구성하는 방법
 * 
 * 1. 자기 자신 class 의 참조변수를 멤버변수로 선언 (private static으로 지정)
 * 
 * 2. 생성자를 private으로 선언 (외부접근 차단 외부 객체 생성 차단)
 * 
 * 3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하기 위한 메서드를 선언
 * 	  (static으로 접근제어자 지정, 보통 getInstance()로 지정
 */
	
	//자기 자신의 class 참조값을 저장하는 멤버변수 선언
	private static MySingleton single;
	
	// 생성자를 private로 지정
	private MySingleton() {
		System.out.println("생성자입니다");
	}
	
	public static MySingleton getInstance() {
		if(single == null) {
			single = new MySingleton();
		}
		return single;
	}
	
	public void displayText() {
		System.out.println("안녕하세요. 싱글턴 객체입니다");
	}
}
