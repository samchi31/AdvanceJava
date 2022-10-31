package javaAnnotationTest;

import java.io.Serializable;

public class Service implements Serializable {
	
	@PrintAnnotation	//(value = "-", count = 20) 생략
	public void method1(/* String name */) throws Exception{
		System.out.println("메서드1");
	}
	
	@PrintAnnotation("%")	//value = % 생략 value 일때만
	public void method2() {
		System.out.println("메서드2");
	}
	
	@PrintAnnotation(value = "#", count = 25)
	public void method3() {
		System.out.println("메서드3");
	}
	
}
