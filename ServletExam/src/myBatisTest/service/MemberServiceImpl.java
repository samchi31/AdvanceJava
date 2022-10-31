package myBatisTest.service;

import java.util.List;

import myBatisTest.dao.IMemberDao;
import myBatisTest.dao.MemberDaoImpl;
import myBatisTest.MemberVO;

public class MemberServiceImpl implements IMemberService{
	
	private static IMemberService memService;
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}
	
	private IMemberDao memDao;
	
	@Override
	public int registMember(MemberVO mv) {
		return memDao.insertMember(mv);
	}

	@Override
	public boolean checkMember(String memId) {
		return memDao.checkMember(memId);
	}

	@Override
	public int modifyMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public int removeMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public List<MemberVO> selectAllMember() {
		return memDao.selectAllMember();
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		return memDao.searchMember(mv);
	}
	@Override
	public MemberVO getMember(String memId) {
		return memDao.getMember(memId);
	}

}
