package javaIOTest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// 사진 파일 복사
public class Homework {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			
			fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("d:/D_Other/Tulips_복사본.jpg", true);
			
			int data = 0;
			
			long startTime = System.currentTimeMillis();
			while((data = fis.read()) != -1) {
				fos.write(data);
			}
			long endTime = System.currentTimeMillis();
			System.out.println("끝 : " + (endTime - startTime));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
