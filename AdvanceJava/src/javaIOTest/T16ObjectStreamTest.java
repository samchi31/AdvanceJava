package javaIOTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16ObjectStreamTest {
// 객체 입출력 보조 스트림 예제 ( 직렬화와 역직렬화 )
	public static void main(String[] args) {
		// Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "서울");
		Member mem3 = new Member("이몽룡", 40, "부산");
		Member mem4 = new Member("성춘향", 50, "광주");
		
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("d:/D_Other/memObj.bin")));
			
			//쓰기 작업
			oos.writeObject(mem1);	// 직렬화
			oos.writeObject(mem2);	// 직렬화
			oos.writeObject(mem3);	// 직렬화
			oos.writeObject(mem4);	// 직렬화
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream("d:/D_Other/memObj.bin")));
			
			Object obj = null;
			while((obj = ois.readObject()) != null) {
				// 읽어온 데이터를 원래의 객체형으로 변환 후 사용
				Member mem = (Member) obj;
				System.out.println(mem.getName());
				System.out.println(mem.getAge());
				System.out.println(mem.getAddr());
				System.out.println("------------------------");
			}
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("끝");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	}
}

class Member implements Serializable{
	// 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있다
	
	/*
	 * transient => 직렬화가 되지 않을 멤버변수 지정 (static 필드도 직렬화 대상에서 제외)
	 * 직렬화 대상이 아닌 필드는 기본값으로 저장
	 */
	private transient String name;
	private transient int age;
	private String addr;
	public Member(String name, int age, String addr) {
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
