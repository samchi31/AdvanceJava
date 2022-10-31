package javaIOTest;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Homework2 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			
			fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("d:/D_Other/Tulips_복사본2.jpg", true);
			bos = new BufferedOutputStream(fos);
			
			int data = 0;
			
			long startTime = System.currentTimeMillis();
			while((data = fis.read()) != -1) {
				bos.write(data);
			}
			long endTime = System.currentTimeMillis();
			
			bos.flush();
			System.out.println("끝 :" + (endTime - startTime));
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
