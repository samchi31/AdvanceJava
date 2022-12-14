package javaCollectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Homework1 {
	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		list.add(new Student("2022003", "김피자", 55, 65, 75));
		list.add(new Student("2022002", "박치킨", 78, 65, 75));
		list.add(new Student("2022004", "이곱창", 85, 63, 71));
		list.add(new Student("2022001", "최족발", 75, 65, 55));
		list.add(new Student("2022008", "오겹살", 95, 67, 76));
		list.add(new Student("2022007", "정떡볶", 80, 80, 78));
		
		for (Student student : list) {
			System.out.println(student);
		}
		System.out.println("-----------------------------------------------------------");

		// 학번 오름차순
		Collections.sort(list);
		for (Student student : list) {
			System.out.println(student);
		}

		System.out.println("===========================================================");

		// 총점 역순 (총점 동일 시 학번 내림차순)
		Collections.sort(list, new TotalDesc());
		for (Student student : list) {
			System.out.println(student);
		}
	}
}

class TotalDesc implements Comparator<Student> {
	
	@Override
	public int compare(Student std1, Student std2) {
		if (std1.getTotal() > std2.getTotal()) {
			return 1;
		} else if (std1.getTotal() == std2.getTotal()) {
			return std1.getStudentNum().compareTo(std2.getStudentNum()) * -1;
		} else {
			return -1;
		}
	}

}

class Student implements Comparable<Student> {
	private String studentNum;
	private String name;
	private int korGrade;
	private int engGrade;
	private int mathGrade;

	public Student(String studentNum, String name, int korGrade, int engGrade, int mathGrade) {
		this.studentNum = studentNum;
		this.name = name;
		this.korGrade = korGrade;
		this.engGrade = engGrade;
		this.mathGrade = mathGrade;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorGrade() {
		return korGrade;
	}

	public void setKorGrade(int korGrade) {
		this.korGrade = korGrade;
	}

	public int getEngGrade() {
		return engGrade;
	}

	public void setEngGrade(int engGrade) {
		this.engGrade = engGrade;
	}

	public int getMathGrade() {
		return mathGrade;
	}

	public void setMathGrade(int mathGrade) {
		this.mathGrade = mathGrade;
	}

	public int getTotal() {
		return this.getKorGrade() + this.getEngGrade() + this.getMathGrade();
	}

	@Override
	public int compareTo(Student student) {
		return this.getStudentNum().compareTo(student.getStudentNum());
	}

	@Override
	public String toString() {
		return String.format(
				"Student [studentNum=%s, name=%s, korGrade=%s, engGrade=%s, mathGrade=%s, total=%d]",
				studentNum, name, korGrade, engGrade, mathGrade, korGrade + engGrade + mathGrade);
	}

}