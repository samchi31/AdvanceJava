package javaThreadTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class T11DisplayCharacterTest {

	/*
	 * 3개의 스레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
	 */
	
	static int currRank = 1;
	public static void main(String[] args) {
		List<DisplayCharacter> disCharList = new ArrayList<DisplayCharacter>();
		disCharList.add(new DisplayCharacter("홍길동"));
		disCharList.add(new DisplayCharacter("이순신"));
		disCharList.add(new DisplayCharacter("강감찬"));
		
		for (Thread th : disCharList) {
			th.start();
		}
		
		for(Thread th : disCharList) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(disCharList);
		System.out.println("경기 결과");
		for (DisplayCharacter dc : disCharList) {
			System.out.println(dc.getRank()+ "\t:\t" + dc.getName()); 
		}
	}
}

class DisplayCharacter extends Thread implements Comparable<DisplayCharacter>{
	private String name;
	private int rank;

	public DisplayCharacter(String name) {
		super(name);
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + ":" + ch);

			try {
				// 200~500 사이 난수
				Thread.sleep((int) (Math.random() * 301 + 200));
				// Thread.sleep(new Random().nextInt(300)+200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(name + " 출력 끝");
			setRank(T11DisplayCharacterTest.currRank++);
		}
	}

	@Override
	public int compareTo(DisplayCharacter o) {
		return new Integer(this.getRank()).compareTo(o.getRank());
	}

}