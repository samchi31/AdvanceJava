package javaGenericEnumTest;

public class T03GenericMethodTest {
	public static void main(String[] args) {
		int result1 = compare(10,20);
		System.out.println(result1);
		
		int result2 = compare(3.14, 3);
		System.out.println(result2);
		
		
	}
	// 제한된 타입 파라미터(Bounded Parameter)
	public static <T extends Number> int compare(T t1, T t2) {
		
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}
