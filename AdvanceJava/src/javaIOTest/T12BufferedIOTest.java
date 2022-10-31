package javaIOTest;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T12BufferedIOTest {
// 성능향상 보조스트림 예제

	public static void main(String[] args) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");

			// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192byte(8kb)로 설정
			// 버퍼크기가 5인 스트림 생성
			bos = new BufferedOutputStream(fos, 5);

			for (char ch = '1'; ch <= '9'; ch++) {
				bos.write(ch);
			}
			
			// 작업을 종료하기전에 버퍼에 남아있는 데이터를 모두 출력시킨다.(close시 자동 호출
			bos.flush();
			
			System.out.println("끝");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
