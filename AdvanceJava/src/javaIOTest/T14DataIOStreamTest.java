package javaIOTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T14DataIOStreamTest {
// 기본타입 입출력 보조스트림
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
		
		// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력한다
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeUTF("홍길동"); 	// 문자열 데이터 출력하기
		dos.writeInt(17); 			// 정수형으로 데이터 출력하기
		dos.writeFloat(3.14f); 		// 실수(float)형으로 데이터 출력하기
		dos.writeDouble(3.14); 		// 실수(double)형으로 데이터 출력하기
		dos.writeBoolean(true); 	// 논리형으로 데이터 출력하기
		
		System.out.println("출력 끝");
		
		dos.close();
		
		// 출력한 데이터 읽어오기
		FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
		DataInputStream dis = new DataInputStream(fis);
		
		System.out.println(dis.readUTF());
		System.out.println(dis.readInt());
		System.out.println(dis.readFloat());
		System.out.println(dis.readDouble());
		System.out.println(dis.readBoolean());
		
		dis.close();
	}
}
