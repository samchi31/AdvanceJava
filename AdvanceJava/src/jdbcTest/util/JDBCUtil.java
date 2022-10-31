package jdbcTest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

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

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "in89", "java");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try { rs.close(); } catch (SQLException e) {}
		}
		if (stmt != null) {
			try { stmt.close(); } catch (SQLException e) {}
		}
		if (pstmt != null) {
			try { pstmt.close(); } catch (SQLException e) {}
		}
		if (conn != null) {
			try { conn.close(); } catch (SQLException e) {}
		}
	}
}
