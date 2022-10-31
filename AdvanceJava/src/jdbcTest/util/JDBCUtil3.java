package jdbcTest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * db.properties 파일의 내용으로 DB접속정보를 설정하는 방법
 * 방법 2) ResourceBundle 객체 이용하기
 */
public class JDBCUtil3 {
	static ResourceBundle bundle;

	// 클래스 로딩 시 한번만 실행됨
	static {
		
		bundle = ResourceBundle.getBundle("db");
		
		try {
			// driver 로드 잘 되었는지 reflection으로 확인
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패");
			e.printStackTrace();
		} 
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("username"), bundle.getString("password"));
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
