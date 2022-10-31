package myBatisTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdbcTest.util.JDBCUtil3;
import myBatisTest.MemberVO;

public class MemberDaoImplForJDBC implements IMemberDao{

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IMemberDao memDao;
	private MemberDaoImplForJDBC() {}
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImplForJDBC();
		}
		return memDao;
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
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
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
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

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
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
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("delete from mymember ");
			builder.append("WHERE  mem_id = ? ");
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		List<MemberVO> list = new ArrayList<MemberVO>();
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
				list.add(new MemberVO(memId, memName, memTel, memAddr));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
//		List<MemberVO> list = Collections.emptyList();
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " select * from mymember where 1=1 ";
			
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				sql += " and mem_id = ? ";
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%'|| ? ||'%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(index++, mv.getMemId());
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(index++, mv.getMemName());
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(index++, mv.getMemTel());
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++, mv.getMemAddr());
			}
					
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				list.add(new MemberVO(memId, memName, memTel, memAddr));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return list;
	}

}
