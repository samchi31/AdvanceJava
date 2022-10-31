package myBatisTest.dao;

import java.util.List;

import myBatisTest.MemberVO;
import myBatisTest.common.dao.MyBatisDao;

public class MemberDaoImpl extends MyBatisDao implements IMemberDao {
	
	private static IMemberDao memDao;
	private MemberDaoImpl() {}
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {
		return insert("member.insertMember", mv);
	}

	@Override
	public boolean checkMember(String memId) {
		int cnt = (int) selectOne("member.checkMember", memId);
		boolean isExist = false;
		if (cnt > 0) {
			isExist = true;
		}
		return isExist;
	}

	@Override
	public int updateMember(MemberVO mv) {
		return update("member.updateMember", mv);
	}

	@Override
	public int deleteMember(String memId) {
		return delete("member.deleteMember", memId);
	}

	@Override
	public List<MemberVO> selectAllMember() {
		return selectList("member.selectAllMember", null);
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		return search("member.searchMember", mv);
	}

}
