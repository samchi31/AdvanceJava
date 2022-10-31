package javaIOTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOStream {
	public static void main(String[] args) throws IOException {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;
		byte[] temp = new byte[4];	// 자료를 읽을 때 사용할 배열

		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readBytes = 0;	// 읽은 배열의 크기 저장
		
		// read(byte[] b) 
		// 반복횟수를 줄일 수 있다, temp를 초기화하지 않으면 기존 배열에 수정된다 -> write(,,)를 사용
		while((readBytes = bais.read(temp)) != -1) {
			//baos.write(temp);
			baos.write(temp, 0, readBytes);
			
			System.out.println("temp : " + Arrays.toString(temp));
		}
		outSrc = baos.toByteArray();	
		
		System.out.println(Arrays.toString(inSrc));
		System.out.println(Arrays.toString(outSrc));
	}
}
