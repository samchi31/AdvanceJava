package javaAnnotationTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		// 선언된 메서드 목록을 조회하고, 메서드를 실행해 보기
		Method[] methodsArr = Service.class.getDeclaredMethods();
		
		//Service service = new Service();
		Service service = (Service) Service.class.newInstance();
		
		for (Method m : methodsArr) {
			System.out.print(m.getName() + " => ");
			Annotation[] annos = m.getDeclaredAnnotations();
			
			for (Annotation anno : annos) {
				if(anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnn = (PrintAnnotation) anno;
					for(int i=0;i<printAnn.count();i++) {
						System.out.print(printAnn.value());
					}
				}
			}
			m.invoke(service);
			System.out.println();
		}
	}
}
