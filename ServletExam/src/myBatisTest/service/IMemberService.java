package myBatisTest.service;

import java.util.List;

import myBatisTest.MemberVO;

/**
 * 컨트롤러의 요청을 받아 처리하는 서비스의 Interface
 * @author PC-07
 *
 */
public interface IMemberService {
	
	/**
	 * 회원 등록 메서드
	 * @param mv 등록할 회원 정보
	 * @return DB 작업이 성공하면 1 이상의 값
	 */
	public int registMember(MemberVO mv);
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내기 위한 메서드
	 * @param memId 확인할 회원의 ID
	 * @return 해당 회원 ID가 있으면 true
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 회원정보를 수정하는 메서드
	 * @param mv update할 회원의 정보가 저장된 객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int modifyMember(MemberVO mv);
	
	/**
	 * 회원Id를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int removeMember(String memId);
	
	/**
	 * 전체 회원 정보를 조회하기 위한 메서드
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
