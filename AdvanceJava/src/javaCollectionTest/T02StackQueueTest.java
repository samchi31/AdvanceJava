package javaCollectionTest;

import java.util.LinkedList;

public class T02StackQueueTest {
	public static void main(String[] args) {
		/*
		 * Stack => 후입선출(LIFO)의 자료구조
		 * Queue => 선입선출(FIFO)
		 */
		
		LinkedList<String> stack = new LinkedList<>();
		
		/*
		 * Stack 메서드
		 * 1) 자료입력 : push(저장할 값)
		 * 2) 자료출력 : pop() => 자료를 꺼내온 후 꺼내온 자료를 stack에서 삭제한다
		 */
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("강감찬");
		System.out.println(stack);
		
		String data = stack.pop();
		System.out.println(data);
		System.out.println(stack.pop());
		System.out.println(stack);
		
		stack.push("성춘향");
		System.out.println(stack);
		System.out.println(stack.pop());
		
		System.out.println("====================================");
		
		LinkedList<String> queue = new LinkedList<>();
		/*
		 * Queue 메서드
		 * 1) 자료입력 : offer(저장할 값)
		 * 2) 자료출력 : poll() => 자료를 queue에서 꺼내온 후 꺼내온 자료를 queue에서 삭제한다
		 */
		
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("강감찬");
		
		System.out.println(queue);
		
		String temp = queue.poll();
		System.out.println(temp);
		System.out.println(queue.poll());
		System.out.println(queue);
		
		if(queue.offer("성춘향")) {
			System.out.println("등록 : 성춘향");
		}
		System.out.println(queue);
		System.out.println(queue.poll());
	}
}
