package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import jdbcTest.util.JDBCUtil2;

public class Homework {
	Scanner scanner = new Scanner(System.in);
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public static void main(String[] args) {
		Homework hotel = new Homework();
		hotel.menu();
	}

	// 클래스 로딩 시 한번만 실행됨
	static {
		try {
			// driver 로드 잘 되었는지 reflection으로 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "in89", "java");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	public void menu() {
		System.out.println("******************************");
		System.out.println("호텔문을 열었습니다");
		System.out.println("******************************");
		while (true) {
			System.out.println("---------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
			System.out.print("메뉴선택 >");
			int num = Integer.parseInt(scanner.next());
			switch (num) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				printAll();
				break;
			case 4:
				System.out.println("종료");
				return;
			}

		}
	}
	
	public void printAll() {
		try {
			conn = getConnection();
			
			String sql = "select * from hotel_mng ";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println();
			System.out.println("------------------------------------------");
			System.out.println(" 방번호\t이 름");
			System.out.println("------------------------------------------");
			while(rs.next()) {
				System.out.println(rs.getInt("room_num") + "\t" + rs.getString("guest_name"));
			}
			System.out.println("------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, pstmt, rs);
		}
	}

	public void checkOut() {
		int roomNum;
		while (true) {
			System.out.println("체크아웃");
			System.out.print("방번호 >");
			roomNum = Integer.parseInt(scanner.next());
			if (!checkRoom(roomNum)) {
				System.out.println("그 방은 비어있습니다");
			} else {
				break;
			}
		}
		
		try {
			conn = getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("delete from hotel_mng ");
			builder.append("where room_num = ? ");
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크아웃 되었습니다.");				
			} else {
				System.out.println("체크아웃 실패");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, pstmt, rs);
		}	
	}

	public void checkIn() {
		int roomNum;
		while (true) {
			System.out.println("체크인");
			System.out.print("방번호 >");
			roomNum = Integer.parseInt(scanner.next());

			if (checkRoom(roomNum)) {
				System.out.println("이미 방이 예약됨");
			} else {
				break;
			}
		}
		System.out.println("이름입력 >");
		String name = scanner.next();

		try {
			conn = getConnection();

			StringBuilder builder = new StringBuilder();
			builder.append(" INSERT INTO hotel_mng (");
			builder.append("     room_num,");
			builder.append("     guest_name");
			builder.append(" ) VALUES (");
			builder.append("     ?,");
			builder.append("     ?");
			builder.append(" )");
			String sql = builder.toString();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			pstmt.setString(2, name);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("체크인 되었습니다");
			} else {
				System.out.println("체크인 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, pstmt, rs);
		}

	}

	/**
	 * 
	 * @param roomNum
	 * @return true 면 이미 예약된방
	 */
	private boolean checkRoom(int roomNum) {
		boolean isCheck = false;
		try {
			conn = getConnection();

			StringBuilder builder = new StringBuilder();
			builder.append(" Select count(*) as cnt from hotel_mng where room_num = ?");
			String sql = builder.toString();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);

			rs = pstmt.executeQuery();

			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				isCheck = true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, pstmt, rs);
		}
		return isCheck;
	}
}
