package myBatisTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import myBatisTest.service.IMemberService;
import myBatisTest.service.MemberServiceImpl;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력		---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class MemberMain {
		
	private IMemberService memService;
	private Scanner scan = new Scanner(System.in); 
	public MemberMain() {
		memService = MemberServiceImpl.getInstance();
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료 검색");
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
		System.out.println(" ID\t이 름\t전화번호\t주소");
		System.out.println("------------------------------------------");
		
		List<MemberVO> list = memService.selectAllMember();
		
		for (MemberVO mv : list) {			
			System.out.println(mv.getMemId() + "\t" + mv.getMemName() + "\t" 
								+ mv.getMemTel() + "\t" + mv.getMemAddr());
		}
		System.out.println("----------------------------------------");
	}

	/**
	 * 회원정보 삭제
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 ID를 입력하세요");
		System.out.println("회원 ID >>");
		
		String memId = scan.next();
		if(memService.removeMember(memId) > 0) {
			System.out.println("회원정보 삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
	}

	/**
	 * 회원정보를 수정하기 위한 메서드
	 */
	private void updateMember() {
		boolean exist = false;
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요");
			System.out.println("회원 ID >>");
			
			memId = scan.next();
			
			exist = memService.checkMember(memId);
			
			if(!exist) {
				System.out.println("회원Id가 존재하지 않음");
				System.out.println("다시 입력");
			}
		}while(!exist);
		
		System.out.println("회원 이름 >>");
		String memName = scan.next();
		
		System.out.println("회원 전화번호 >>");
		String memTel = scan.next();
		
		scan.nextLine();//버퍼비우기
		
		System.out.println("회원 주소 >>");
		String memAddr = scan.nextLine();
		
		
		if(memService.modifyMember(new MemberVO(memId, memName, memTel, memAddr)) > 0) {
			System.out.println("회원정보 수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}

	/**
	 * 회원정보를 추가하는 메서드
	 */
	private void insertMember() {
		boolean exist = false;
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요");
			System.out.println("회원 ID >>");
			
			memId = scan.next();
			
			exist = memService.checkMember(memId);
			
			if(exist) {
				System.out.println("회원Id가 이미 존재");
				System.out.println("다시 입력");
			}
		}while(exist);
		
		System.out.println("회원 이름 >>");
		String memName = scan.next();
		
		System.out.println("회원 전화번호 >>");
		String memTel = scan.next();
		
		scan.nextLine();//버퍼비우기
		
		System.out.println("회원 주소 >>");
		String memAddr = scan.nextLine();
		
		
		if(memService.registMember(new MemberVO(memId, memName, memTel, memAddr)) > 0) {
			System.out.println("회원정보 추가 성공");
		} else {
			System.out.println("추가 실패");
		}
	}
	
	/**
	 * 회원정보를 검색하기 위한 메서드
	 */
	public void searchMember() {
	// 검색할 회원 ID, 회원이름, 전화번호, 주소등을 입력하면 입력한 정보만 사용하여 검색하는 기능 구현
	// 주소는 입력한 값이 포함만 되어도 검색가능하도록
	// 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다
		
		scan.nextLine(); 	// 버퍼비우기
		System.out.println();
		System.out.println("검색할 회원 정보를 입력하세요");
		
		System.out.println("회원 ID >>");		
		String memId = scan.nextLine().trim();
		
		System.out.println("회원 이름 >>");		
		String memName = scan.nextLine().trim();
		
		System.out.println("회원 전화번호 >>");		
		String memTel = scan.nextLine().trim();
		
		System.out.println("회원 주소 >>");		
		String memAddr = scan.nextLine().trim();
		
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println(" ID\t이 름\t전화번호\t주소");
		System.out.println("------------------------------------------");
		
		List<MemberVO> list = memService.searchMember(new MemberVO(memId, memName, memTel, memAddr));
		
		for (MemberVO mv : list) {			
			System.out.println(mv.getMemId() + "\t" + mv.getMemName() + "\t" 
								+ mv.getMemTel() + "\t" + mv.getMemAddr());
		}
		System.out.println("----------------------------------------");
	}
	
	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}

}