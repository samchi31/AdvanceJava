package myBatisHomework.dao;

import java.util.List;
import myBatisHomework.BoardVO;

public class BoardDao extends BatisDao implements IBoardDao {
	private static BoardDao instance;
	private BoardDao() {}
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	

	@Override
	public int insertMemo(BoardVO mv) {
		return insert("board.insert", mv);
	}

	@Override
	public boolean checkMemo(int no) {
		boolean isExist = false;
		if((int)selectOne("board.check", no) > 0) {
			isExist = true;
		}
		return isExist;
	}

	@Override
	public int updateMemo(BoardVO mv) {
		return update("board.update", mv);
	}

	@Override
	public int deleteMemo(int no) {
		return delete("board.delete", no);
	}

	@Override
	public List<BoardVO> selectAllMemo() {
		return selectList("board.selectList", null);
	}

	@Override
	public List<BoardVO> searchMemo(BoardVO mv) {
		return selectList("board.search", mv);
	}

}
