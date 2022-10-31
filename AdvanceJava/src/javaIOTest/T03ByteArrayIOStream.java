package javaIOTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03ByteArrayIOStream {
	public static void main(String[] args) throws IOException {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

//		// 직접 복사하기
//		outSrc = new byte[inSrc.length];
//		for (int i = 0; i < inSrc.length; i++) {
//			outSrc[i] = inSrc[i];
//		}	
		
//		// arraycopy로 복사하기
//		outSrc = new byte[inSrc.length];
//		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
		
		// 스트림 클래스 이용하기
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data = 0;	// 읽어온 바이트 데이터를 저장할 변수
		// read() => byte 단위로 데이터를 읽어와 int 형으로 반환한다. 더이상 읽어올 자료가 없으면 -1 반환
		while((data = bais.read()) != -1) {
			baos.write(data);// 데이터 출력
		}
		
		outSrc = baos.toByteArray();
		
		bais.close();
		baos.close();
		
		System.out.println(Arrays.toString(inSrc));
		System.out.println(Arrays.toString(outSrc));
	}
}
