package javaThreadTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Homework {

	public static int finalRank = 1;

	public static void main(String[] args) {

		List<Horse> horseLst = new ArrayList<Horse>();
		for (int i = 0; i < 10; i++) {
			horseLst.add(new Horse(String.format("%s번", i + 1)));
		}
		List<Thread> threadLst = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			threadLst.add(new Thread(horseLst.get(i)));
		}

		// print Daemon thread
		Thread printThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("*************************************************************");
					for (Horse horse : horseLst) {
						horse.printTrack(horse.getPos());
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});
		printThread.setDaemon(true);

		for (Thread th : threadLst) {
			th.start();
		}
		printThread.start();
		
		
		for (Thread th : threadLst) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 최종 결과
		System.out.println();
		System.out.println("========================================================");
		Collections.sort(horseLst);
		for (Horse horse : horseLst) {
			System.out.println(horse);
		}
	}
}

class Horse implements Comparable<Horse>, Runnable {
	private String name;
	private int rank;

	private int pos;

	public Horse(String name) {
		this.name = name;
	}

	public String getName() {
		return String.format("%3s", name);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPos() {
		return this.pos;
	}

	@Override
	public int compareTo(Horse o) {
		return new Integer(this.rank).compareTo(o.rank);
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				// printTrack(i);
				this.pos = i;
				Thread.sleep(new Random().nextInt(700) + 300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setRank(Homework.finalRank++);
	}

	public void printTrack(int pos) {
		System.out.print(String.format("%3s", name) + " : ");
		for (int i = 0; i < 50; i++) {
			if (i == pos) {
				System.out.print(">");
			} else {
				System.out.print("-");
			}
		}
		System.out.println();
	}

	@Override
	public String toString() {
		return String.format("%3s등", getRank()) + " : " + getName();
	}
	
}
