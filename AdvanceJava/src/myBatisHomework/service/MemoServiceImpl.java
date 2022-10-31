package myBatisHomework.service;

import java.util.List;

import myBatisHomework.BoardVO;
import myBatisHomework.dao.BoardDao;

public class MemoServiceImpl implements IMemoService{
	private BoardDao dao;
	private static MemoServiceImpl instance;
	private MemoServiceImpl() {
		dao = BoardDao.getInstance();
	}
	public static MemoServiceImpl getInstance() {
		if(instance == null) {
			instance = new MemoServiceImpl();
		}
		return instance;
	}
	
	
	@Override
	public int registMemo(BoardVO mv) {
		return dao.insertMemo(mv);
	}

	@Override
	public boolean checkMemo(int no) {
		return dao.checkMemo(no);
	}

	@Override
	public int modifyMemo(BoardVO mv) {
		return dao.updateMemo(mv);
	}

	@Override
	public int removeMemo(int no) {
		return dao.deleteMemo(no);
	}

	@Override
	public List<BoardVO> selectAllMemo() {
		return dao.selectAllMemo();
	}

	@Override
	public List<BoardVO> searchMemo(BoardVO mv) {
		return dao.searchMemo(mv);
	}

}
