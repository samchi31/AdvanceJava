package myBatisHomework.service;

import java.util.List;

import myBatisHomework.BoardVO;

/**
 * 컨트롤러의 요청을 받아 처리하는 서비스의 Interface
 * @author PC-07
 *
 */
public interface IMemoService {
	
	public int registMemo(BoardVO mv);
	
	public boolean checkMemo(int no);
	
	public int modifyMemo(BoardVO mv);
	
	public int removeMemo(int no);
	
	public List<BoardVO> selectAllMemo();
	
	public List<BoardVO> searchMemo(BoardVO mv);
}
