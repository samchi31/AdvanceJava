package javaIOTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class T10FileEncodingTest {
/*
 * 한글 인코딩 방식에 대하여
 * 
 * 한글 인코딩 방식은 크게 UTF-8 과 EUC-KR 방식 크게 두가지이다
 * 원래 한글 윈도우는 CP949 방식을 사용했는데, 마이크로소프트에서 EUC-KR 방식에서 확장하였기 때문에 MS949라고도 한다
 * 
 * 한글 윈도우의 메모장에서 ANSI 인코딩이란 CP949(Code Page 949)를 말한다
 * CP949는 EUC-KR의 확장이다 (하위 호환가능)
 * 
 * - MS949 => 윈도우의 기본한글 인코딩 방식(ANSI 계열)
 * - UTF-8 => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자 : 1byte, 한글 : 3byte)
 * - US-ASCII => 영문전용 인코딩 방식
 * - 유니코드 => 서로다른 문자 인코딩을 사용하는 컴퓨터간의 문서교환에 어려움을 겪게되고, 
 * 				 전 세계의 문자를 하나의 통일된 문자집합(CharSet)으로 표현하게 됨
 * 
 * ANSI 는 처음에 영어를 표기하기 위해 만든 코드로 규격자체에 한글이 없었다가
 * EUC-KR, CP949 라는 식으로 한글을 포함시켜 사용함
 */
	public static void main(String[] args) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/test_ansi.txt");
//			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			
			// 파일 인코딩 정보를 이용하여 읽어오기
			// InputStreamReader 객체는 파일의 인코딩 방식을 지정할 수 있다
			// 형식) new InputStreamReader(바이트기반스트림객체, 인코딩방식);
//			isr = new InputStreamReader(fis, "CP949");
//			isr = new InputStreamReader(fis, "UTF-8");
			isr = new InputStreamReader(fis, "EUC-KR");
			
			int data = 0;
			while ((data = isr.read()) != -1) {
				System.out.print((char)data);
			}
			System.out.println("\n끝");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
//				fis.close();
				isr.close();	// 보조 스트림만 닫아도 된다
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
