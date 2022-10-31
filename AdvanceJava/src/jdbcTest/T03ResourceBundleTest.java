package jdbcTest;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class T03ResourceBundleTest {
/*
 * ResourceBundle 객체 => 확장자가 properties인 파일 정보를 읽어와 
 * key 값과 value 값을 분리한 정보를 갖는 객체
 * 국제화(나라별)로 관리하기 위함
 * 
 * => 읽어올 파일은 'key값 = value값' 형태로 되어있어야 한다
 */
	public static void main(String[] args) {
		// ResourceBundle 객체를 이용하여 파일 읽어오기
		/*
		 * ResourceBundle 객체 생성하기
		 * => 파일을 지정할 때는 '파일명'만 지정하고 '확장자'는 지정하지 않는다 (항상 .properties)
		 */
		
		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.JAPANESE);
		
		//key 값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = bundle.getString(key);
			
			System.out.println(key + " : " + value);
		}
	}
}
