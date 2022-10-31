package jdbcTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbcTest.util.JDBCUtil3;

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
		4. 전체 자료 출력	---> select
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
public class T01MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	// log4j2 단독으로 직접 로그를 남기기 위한 로거 객체 생성하기
	private static final Logger SQL_LOGGER = LogManager.getLogger("log4jexam.sql.Query");
	private static final Logger PARAM_LOGGER = LogManager.getLogger("log4jexam.sql.Parameter");
	private static final Logger RESULT_LOGGER = LogManager.getLogger(T01MemberInfoTest.class);
	
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
		System.out.println("  5. 작업 끝.");
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
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	/**
	 * 정체 회원정보 출력
	 */
	private void selectAllMember() {
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println(" ID\t이 름\t전화번호\t주소");
		System.out.println("------------------------------------------");
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember ";
			
			stmt = conn.createStatement();			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("----------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원정보 삭제
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 ID를 입력하세요");
		System.out.println("회원 ID >>");
		
		String memId = scan.next();
		try {
			conn = JDBCUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("delete from mymember ");
			builder.append("WHERE  mem_id = ? ");
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("회원정보 삭제 완료");
			} else {
				System.out.println("삭제 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
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
			
			exist = checkMember(memId);
			
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
		
		
		try {
			conn = JDBCUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("UPDATE mymember ");
			builder.append("   SET mem_name = ?, ");
			builder.append("       mem_tel = ?, ");
			builder.append("       mem_addr = ? ");
			builder.append("WHERE  mem_id = ? ");
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("회원정보 수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
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
			
			exist = checkMember(memId);
			
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
		
		try {
			conn = JDBCUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append(" INSERT INTO mymember (");
			builder.append("     mem_id,");
			builder.append("     mem_name,");
			builder.append("     mem_tel,");
			builder.append("     mem_addr,");
			builder.append("     reg_dt");
			builder.append(" ) VALUES (");
			builder.append("     ?,");
			builder.append("     ?,");
			builder.append("     ?,");
			builder.append("     ?,");
			builder.append("     SYSDATE");
			builder.append(" )");
			String sql = builder.toString();
			
			SQL_LOGGER.debug("sql : " + sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			PARAM_LOGGER.debug("memID : " + memId 
							+ ", memName : " + memName
							+ ", memTel : " + memTel
							+ ", memAddr : " + memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			RESULT_LOGGER.info("결과값 : {}", cnt);
			
			if(cnt > 0) {
				System.out.println("회원정보 추가 성공");
			} else {
				System.out.println("추가 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}
	
	/**
	 * 회원ID를 이용하여 회원이 존재하는지 체크하기 위한 메서드
	 * @param memId 체크할 회원 ID
	 * @return 존재하면 true
	 */
	private boolean checkMember(String memId) {
		boolean isCheck = false;
		try {
			conn = JDBCUtil3.getConnection();
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT COUNT(*) AS cnt ");
			builder.append("FROM mymember ");
			builder.append("WHERE mem_id = ? ");
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0 ) {
				isCheck = true;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}	
		
		return isCheck;
	}
	
	public static void main(String[] args) {
		T01MemberInfoTest memObj = new T01MemberInfoTest();
		memObj.start();
	}

}






