package javaIOTest;

import java.io.FileInputStream;
import java.io.IOException;

// 파일 입력
public class T05FileStreamTest {
	public static void main(String[] args) {
		// FileInputStream 객체를 이용한 파일 내용 일기
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/test2.txt");
			int data = 0;
			while((data = fis.read()) != -1) {
				//읽어온 내용 출력하기
				System.out.print((char) data);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
