package mvcHomework.service;

import java.util.List;

import mvcHomework.BoardVO;
import mvcHomework.dao.IBoardDao;
import mvcHomework.dao.MemoDaoImpl;

public class MemoServiceImpl implements IMemoService{

	private IBoardDao memDao;
	
	private MemoServiceImpl() {
		memDao = MemoDaoImpl.getInstance();
	}
	private static IMemoService instance;
	public static IMemoService getInstance() {
		if(instance == null) {
			instance = new MemoServiceImpl();
		}
		return instance;
	}
	
	@Override
	public int registMemo(BoardVO mv) {
		return memDao.insertMemo(mv);
	}

	@Override
	public boolean checkMemo(int no) {
		return memDao.checkMemo(no);
	}

	@Override
	public int modifyMemo(BoardVO mv) {
		return memDao.updateMemo(mv);
	}

	@Override
	public int removeMemo(int no) {
		return memDao.deleteMemo(no);
	}

	@Override
	public List<BoardVO> selectAllMemo() {
		return memDao.selectAllMemo();
	}

	@Override
	public List<BoardVO> searchMemo(BoardVO mv) {
		return memDao.searchMemo(mv);
	}

}
