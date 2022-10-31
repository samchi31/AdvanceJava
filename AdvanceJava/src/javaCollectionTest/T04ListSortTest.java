package javaCollectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04ListSortTest {
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1,"홍길동","010-1111-1111"));
		memList.add(new Member(5,"변학도","010-1111-2222"));
		memList.add(new Member(9,"성춘향","010-1111-3333"));
		memList.add(new Member(3,"이순신","010-1111-4444"));
		memList.add(new Member(6,"강감찬","010-1111-5555"));
		memList.add(new Member(2,"일지매","010-1111-6666"));
		
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("===============================================");
		Collections.sort(memList);
		
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("===============================================");
		// 외부정렬기준을 이용한 정렬
		Collections.sort(memList, new SortNumDesc());
		for (Member mem : memList) {
			System.out.println(mem);
		}
	}
}

/*
 * Member의 번호의 내림차순으로 정렬하는 외부정렬자 클래스
 */
class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
		if(mem1.getNum() > mem2.getNum()) {
			return -1;
		} else if(mem1.getNum() == mem2.getNum()) {
			return 0;
		} else {
			return 1;
		}
	}
	
}

/*
 * 회원이름을 기준으로 오름차순 정렬이 될 수 있도록 클래스 정의 하기
 */
class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public int compareTo(Member mem) {
		return this.getName().compareTo(mem.getName());
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}	
	
}