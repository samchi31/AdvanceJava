package javaGenericEnumTest;

import java.util.ArrayList;
import java.util.List;

public class T04WildCardTest {
	/*
	 * 와일드 카드에 대하여...
	 * 
	 * 와일드(?)는 제너릭 타입을 이용한 타입 안전한 코딩을 위해 사용되는 특별한 종류의 인수(Argument)로서 변수선언, 객체생성 및 멧드
	 * 정의할 때 사용된다 (제너릭 타입 선언시에는 사용할 수 없다)
	 * 
	 * <? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능 
	 * <? super T> => 와일드 카드의 하한 제한. T와 그 조상들만 가능 
	 * <?> => 허용가능한 모든 타입이 가능
	 */

	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox<>(); // 과일 상자
		FruitBox<Apple> appleBox = new FruitBox<>(); // 사과 상자
		//FruitBox<Garbage> garbageBox = new FruitBox<>(); // 사과 상자

		fruitBox.add(new Apple());
		fruitBox.add(new Grape());

		appleBox.add(new Apple());
		appleBox.add(new Apple());
		
//		garbageBox.add(new Garbage());
//		garbageBox.add(new Garbage());
		
		Juicer.makeJuice(fruitBox);
		Juicer.makeJuice(appleBox);
		//Juicer.makeJuice(garbageBox);
	}
}

class Juicer {
	//static <T extends Fruit> void makeJuice(FruitBox<T> box) {
	static void makeJuice(FruitBox<?> box) {
		String fruitListStr = "";	// 과일 목록
		int cnt = 0;
		for(Object f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
			} else {
				fruitListStr += "," + f;
			}
			cnt++;
		}
		System.out.println(fruitListStr + "=> 주스완성");
	}
}

class Fruit {
	private String name;

	public Fruit() {
	}

	public Fruit(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("과일 ( %s )", name);
	}

}

class Apple extends Fruit {
	public Apple() {
		super("사과");
	}
}

class Grape extends Fruit {
	public Grape() {
		super("포도");
	}
}

class FruitBox<T extends Fruit> {
	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<T>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}

	public void add(T fruit) {
		fruitList.add(fruit);
	}
}

class Garbage {
	private String name;
	public Garbage() {
		this.name = "쓰레기";
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "쓰레기";
	}
}