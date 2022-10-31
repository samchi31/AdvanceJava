package javaGenericEnumTest;

import java.util.ArrayList;
import java.util.List;

public class T05WildCardTest {
	public static void main(String[] args) {
		Cart<Food> foodCart = new Cart<>();
		foodCart.add(new Meat("돼지고기", 5000));
		foodCart.add(new Meat("소고기", 25000));
		foodCart.add(new Juice("토마토주스", 1000));
		foodCart.add(new Coffee("아메리카노", 1500));
		
		Cart<Meat> meatCart = new Cart<>();
		meatCart.add(new Meat("돼지고기", 5000));
		meatCart.add(new Meat("소고기", 25000));
		
		Cart<Drink> drinkCart = new Cart<>();
		drinkCart.add(new Drink("토마토주스", 1000));
		drinkCart.add(new Drink("아메리카노", 1500));
		
		displayCartItemInfo(foodCart);
		displayCartItemInfo(meatCart);
		displayCartItemInfo(drinkCart);
		
		//displayCartItemInfo2(foodCart);
		//displayCartItemInfo2(meatCart);
		displayCartItemInfo2(drinkCart);
		
		displayCartItemInfo3(foodCart);
		displayCartItemInfo3(meatCart);
		//displayCartItemInfo3(drinkCart);
	
	}

	// 장바구니 항목 조회를 위한 메서드 (모든 항목)
	public static void displayCartItemInfo(Cart<?> cart) {
		System.out.println();
		for (Object item : cart.getList()) {
			System.out.println(item);
		}
	}

	// 장바구니 항목 조회를 위한 메서드 (음료 하위 항목)
	public static void displayCartItemInfo2(Cart<? extends Drink> cart) {
		System.out.println();
		for (Object item : cart.getList()) {
			System.out.println(item);
		}
	}

	// 장바구니 항목 조회를 위한 메서드 (고기 상위 항목)
	public static void displayCartItemInfo3(Cart<? super Meat> cart) {
		System.out.println();
		for (Object item : cart.getList()) {
			System.out.println(item);
		}
	}
}

class Food {
	private String name; // 음식이름
	private int price; // 가격

	public Food(String name, int price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%s ( %s원 )", name, price);
	}
}

class Meat extends Food {

	public Meat(String name, int price) {
		super(name, price);
	}
}

class Drink extends Food {

	public Drink(String name, int price) {
		super(name, price);
	}

}

class Juice extends Drink {

	public Juice(String name, int price) {
		super(name, price);
	}

}

class Coffee extends Drink {

	public Coffee(String name, int price) {
		super(name, price);
	}

}

class Cart<T> {
	private List<T> list = new ArrayList<T>();

	public List<T> getList() {
		return list;
	}

	public void add(T item) {
		list.add(item);
	}
}