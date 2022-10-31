package mvcHomework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdbcTest.util.JDBCUtil3;
import mvcHomework.BoardVO;

public class MemoDaoImpl implements IBoardDao{

	private static IBoardDao memDao;
	private MemoDaoImpl() {}
	public static IBoardDao getInstance() {
		if(memDao == null) {
			memDao = new MemoDaoImpl();
		}
		return memDao;
	}
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertMemo(BoardVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append(" insert into jdbc_board ( board_no, board_title, board_writer, board_date, board_content)");
			builder.append(" values (board_seq.nextVal, ?, ?, sysdate, ?)");
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getTitle());
			pstmt.setString(2, mv.getWriter());
			pstmt.setString(3, mv.getContent());
			
			cnt = pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public boolean checkMemo(int no) {
		boolean isCheck = false;
		try {
			conn = JDBCUtil3.getConnection();
			StringBuilder builder = new StringBuilder();
			builder.append(" select count(*) as cnt");
			builder.append(" from jdbc_board where board_no = ?");
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
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
	public int updateMemo(BoardVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("update jdbc_board  ");
			builder.append("set board_title = ?, board_writer = ? , board_date = sysdate, board_content = ? ");
			builder.append("where board_no = ? ");
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getTitle());
			pstmt.setString(2, mv.getWriter());
			pstmt.setString(3, mv.getContent());
			pstmt.setInt(4, mv.getNo());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMemo(int no) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("delete from jdbc_board ");
			builder.append("WHERE  board_no = ? ");
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> selectAllMemo() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from jdbc_board ";
			
			stmt = conn.createStatement();			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new BoardVO(rs.getInt("board_no"), rs.getString("board_title"),
						rs.getString("board_writer"), rs.getString("board_content"), rs.getDate("board_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<BoardVO> searchMemo(BoardVO mv) {
//		List<MemberVO> list = Collections.emptyList();
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " select * from jdbc_board where 1=1 ";
			
			if(mv.getNo() != 0) {
				sql += " and board_no = ? ";
			}
			if(mv.getTitle() != null && !mv.getTitle().equals("")) {
				sql += " and board_title = ? ";
			}
			if(mv.getWriter() != null && !mv.getWriter().equals("")) {
				sql += " and board_writer = ? ";
			}
			if(mv.getContent() != null && !mv.getContent().equals("")) {
				sql += " and board_content like '%'|| ? ||'%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			if(mv.getNo() != 0) {
				pstmt.setInt(index++, mv.getNo());
			}
			if(mv.getTitle() != null && !mv.getTitle().equals("")) {
				pstmt.setString(index++, mv.getTitle());
			}
			if(mv.getWriter() != null && !mv.getWriter().equals("")) {
				pstmt.setString(index++, mv.getWriter());
			}
			if(mv.getContent() != null && !mv.getContent().equals("")) {
				pstmt.setString(index++, mv.getContent());
			}
					
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new BoardVO(rs.getInt("board_no"), rs.getString("board_title"),
						rs.getString("board_writer"), rs.getString("board_content"), rs.getDate("board_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return list;
	}

}
