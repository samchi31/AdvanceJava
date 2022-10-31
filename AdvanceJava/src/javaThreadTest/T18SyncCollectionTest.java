package javaThreadTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T18SyncCollectionTest {
/*
 * Vector, HashTable 등의 예전부터 존재하던 Collection들은 내부에 동기화 처리가 되어있다
 * 그런데 최근에 새로 구성된 Collection들은 동기화 처리가 되어있지 않다
 * 그래서 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후 사용해야한다
 */
	
//	// 동기화 하지 않을 경우
//	private static List<Integer> list1 = new ArrayList<>();
	
	// 동기화 하는 경우
	private static List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				for(int i=1;i<=10000;i++) {
					list1.add(i);
				}
			}
			
		};
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r)
		};
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(list1.size());
	}
}
