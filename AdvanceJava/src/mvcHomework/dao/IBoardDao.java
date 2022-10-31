package mvcHomework.dao;

import java.util.List;

import mvcHomework.BoardVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성하여 서비스에 전달하는 DAO Interface
 * @author PC-07
 *
 */
public interface IBoardDao {
	
	public int insertMemo(BoardVO mv);
	
	public boolean checkMemo(int no);
	
	public int updateMemo(BoardVO mv);
	
	public int deleteMemo(int no);
	
	public List<BoardVO> selectAllMemo();
	
	public List<BoardVO> searchMemo(BoardVO mv);
}
