package jdbcTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/*
 * 외부의 properties 파일을 읽어와 Properties 객체로 처리하기
 * (properties 파일.. .config 나 .ini 파일과 비슷한 거인듯..)
 */
public class T02PropertiesTest {
	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		
		// 읽어올 파일명을 이용한 File 객체 생성하기
		File file = new File("res/db.properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			
			// Properties 객체로 파일 내용 읽기
			prop.load(fis);
			// 파일 내용을 읽어와 key와 value 값으로 분류한 후 객체에 담아준다
			
			// 읽어온 데이터 출력하기
			// key값만 읽어와 Enumeration 객체로 반환하기
			Enumeration<String> keys = (Enumeration<String>)prop.propertyNames();
			
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " : " + value);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
