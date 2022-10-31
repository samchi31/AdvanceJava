package javaIOTest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07FileWriterTest {
	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장
		
		// 콘솔(표준입력장치)과 연결된 입력용 문자 스트림 객체 생성하기
		// InputStreamReader => 바이트 기반 스트림(InputStream)을 문자 기반 스트림(Reader)으로 반환해주는 보조 스트림
		// (보조 스트림 : 메인(기반) 스트림이 필요하다 지금은 System.in )
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null;
		
		try {
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요");
			
			// 콘솔에서 입력할 때 입력의 끝을 나타내는 표시는 Ctrl + z키를 누르면 된다
			// 엔터 키를 눌러야 파일 저장 됨
			while((data = isr.read()) != -1) {
				fw.write(data);
			}
			
			System.out.println("작업 끝");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
