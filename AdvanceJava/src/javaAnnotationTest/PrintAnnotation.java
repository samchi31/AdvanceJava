package javaAnnotationTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Annotation에 대하여...
 * 프로그램 소스코드 안에 다른 프록르매을 위한 정보를 미리 약속된 형식으로 포함시킨 것(JDK 1.5 부터 지원)
 * 
 * 주석처럼 프로그램에 영향을 미치지 않으면서도 다른 프로그램에게 유용한 정보를 제공함
 * 
 * 종류 :
 * 1. 표준 Annotation
 * 2. 메타 Annotation(Annotation 위한 Annotation, 즉 Annotation을 정의할 때 사용하는 Annotation)
 * 
 * Annotation 요소의 규칙
 * 1. 요소의 타입은 기본형, String, enum, annotation, class만 허용함
 * 2. ()안에 매개변수를 선언할 수 없다
 * 3. 예외를 선언할 수 없다
 * 4. 요소의 타입에 타입 파라미터(제너릭타입글자)를 사용할 수 없다
 * 
 */
// 메타
@Target({ElementType.METHOD, ElementType.TYPE})	// 적용대상 지정 배열로 여러 대상 가능
@Retention(RetentionPolicy.RUNTIME) // 유지기간 지정 (기본: class)
// 표준
public @interface PrintAnnotation {
	int id = 100;	// 상수 선언 가능 static final
	String value() default "-";	// default 값 생략 가능
	int count() default 20;
}
