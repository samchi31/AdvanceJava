package mvcHomework;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import jdbcTest.util.JDBCUtil3;
import mvcHomework.service.IMemoService;
import mvcHomework.service.MemoServiceImpl;

public class BoardMain {
		
	private IMemoService memService;
	private Scanner scan = new Scanner(System.in); 
	public BoardMain() {
		memService = MemoServiceImpl.getInstance();
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 글 삭제");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					selectAllMember();
					break;
				case 5 :  // 자료 검색
					searchMember();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	/**
	 * 정체 회원정보 출력
	 */
	private void selectAllMember() {
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println(" 번호\t제목\t\t\t작성자\t날짜\t내용");
		System.out.println("------------------------------------------");
		
		List<BoardVO> list = memService.selectAllMemo();
		
		for (BoardVO mv : list) {			
			System.out.println(mv.getNo()+ "\t" +mv.getTitle() + "\t\t\t" + mv.getWriter() + "\t" 
								+ mv.getDate() + "\t" + mv.getContent());
		}
		System.out.println("----------------------------------------");
	}

	/**
	 * 회원정보 삭제
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 메모 번호를 입력하세요");
		System.out.println("번호 >>");
		
		int no = Integer.parseInt(scan.next());
		if(memService.removeMemo(no) > 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
	}

	private void updateMember() {
		boolean exist = false;
		int no = 0;
		do {
			System.out.println();
			System.out.println("수정할 메모의 번호를 입력하세요");
			System.out.println("번호 >>");
			
			no = Integer.parseInt(scan.next());
			
			exist = memService.checkMemo(no);
			
			if(!exist) {
				System.out.println("해당하는 메모 없음");
				System.out.println("다시 입력");
			}
		}while(!exist);
		
		scan.nextLine();
		System.out.println("제목 >>");
		String title = scan.nextLine();
		
		System.out.println("작성자 >>");
		String writer = scan.nextLine();		
		
		System.out.println("내용 >>");
		String content = scan.nextLine();
		
		
		if(memService.modifyMemo(new BoardVO(no, title, writer, content)) > 0) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}

	/**
	 * 회원정보를 추가하는 메서드
	 */
	private void insertMember() {
		boolean exist = false;
		
		System.out.println();
		
		scan.nextLine();
		System.out.println("제목 >>");
		String title = scan.nextLine();
		System.out.println("작성자 >>");
		String writer = scan.nextLine();
		
		System.out.println("내용 >>");
		String content = scan.nextLine();
		
		
		if(memService.registMemo(new BoardVO(0, title, writer, content)) > 0) {
			System.out.println("추가 성공");
		} else {
			System.out.println("추가 실패");
		}
	}
	
	/**
	 * 회원정보를 검색하기 위한 메서드
	 */
	public void searchMember() {
		
		scan.nextLine(); 	// 버퍼비우기
		System.out.println();
		System.out.println("검색할 메모 정보를 입력하세요");
		
		System.out.println("메모 번호 >>");		
		int no = 0;
		if(!scan.nextLine().trim().equals("")) {
			no = Integer.parseInt(scan.nextLine().trim());
		}
		
		System.out.println("제목 >>");		
		String title = scan.nextLine().trim();
		
		System.out.println("작성자 >>");		
		String writer = scan.nextLine().trim();
		
		System.out.println("내용 >>");		
		String content = scan.nextLine().trim();
		
		List<BoardVO> list = memService.searchMemo(new BoardVO(no, title, writer, content));
		
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println(" 번호\t제목\t\t\t작성자\t날짜\t내용");
		System.out.println("------------------------------------------");
		
		for (BoardVO mv : list) {			
			System.out.println(mv.getNo()+ "\t" +mv.getTitle() + "\t\t\t" + mv.getWriter() + "\t" 
								+ mv.getDate() + "\t" + mv.getContent());
		}
		System.out.println("----------------------------------------");
	}
	
	public static void main(String[] args) {
		BoardMain memObj = new BoardMain();
		memObj.start();
	}

}