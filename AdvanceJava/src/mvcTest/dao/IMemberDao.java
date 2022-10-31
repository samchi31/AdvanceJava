package mvcTest.dao;

import java.util.List;

import mvcTest.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성하여 서비스에 전달하는 DAO Interface
 * @author PC-07
 *
 */
public interface IMemberDao {
	
	/**
	 * MemberVO에 담겨진 데이터를 DB에 insert하는 메서드
	 * @param mv DB에 insert할 데이터가 저장된 객체
	 * @return DB 작업이 성공하면 1 이상의 값
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내기 위한 메서드
	 * @param memId 확인할 회원의 ID
	 * @return 해당 회원 ID가 있으면 true
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 하나의 MemberVO 객체를 이용하여 DB정보를 update하는 메서드
	 * @param mv update할 회원의 정보가 저장된 객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원Id를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * DB에 테이블에 존재하는 전체 회원 정보를 조회하기 위한 메서드
	 * @return 회원정보를 담고있는 List타입의 객체
	 */
	public List<MemberVO> selectAllMember();
	
	/**
	 * MemberVO에 담긴 데이터를 이용하여 회원정보를 검색하는 메서드
	 * @param mv 검색정보 데이터
	 * @return 검색된 결과 리스트
	 */
	public List<MemberVO> searchMember(MemberVO mv);
}
