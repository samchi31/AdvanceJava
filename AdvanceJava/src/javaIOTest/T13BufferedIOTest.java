package javaIOTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class T13BufferedIOTest {
	public static void main(String[] args) {
		// 문자기반의 Buffered 스트림 사용
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader("src/javaIOTest/T12BufferedIOTest.java");
//			int data = 0;
//			while((data = fr.read()) != -1) {
//				System.out.print((char)data);
//			}
			
			// 한줄씩 읽을 수 있도록 해주는 readLine()을 이용하기 위해
			// BufferedReader 사용하기
			br = new BufferedReader(fr);
			String temp = "";
			int cnt = 1;
			while((temp = br.readLine()) != null) {
				System.out.printf("%4d : %s\n", cnt++, temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
//				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
