package javaLambdaTest;

@FunctionalInterface
public interface LambdaInterface1 {
	//반환값도 없고 매개변수도 없는 추상메서드
	public void test();
//	public void test2();	// 어노테이션 덕분에 에러 확인 가능
}

@FunctionalInterface
interface LambdaInterface2 {
	// 반환값도 없고 매개변수 있는 추상메서드
	public void test(int a);
}

@FunctionalInterface
interface LambdaInterface3 {
	// 반환값도 있고 매개변수도 있는 추상메서드
	public int test(int a, int b);
}
