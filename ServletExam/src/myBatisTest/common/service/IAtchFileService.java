package myBatisTest.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import myBatisTest.common.AtchFileVO;

/**
 * 첨부파일 저장을 위한 공통 서비스용 인터페이스
 */
public interface IAtchFileService {
	
	/**
	 * 첨부파일 목록을 저장하기 위한 메서드
	 * @param req Part 객체를 꺼내기 위한 요청 객체
	 * @return
	 * @throws Exception
	 */
	public AtchFileVO saveAtchFileList(HttpServletRequest req);
	
	/**
	 * 첨부파일 목록 조회하기
	 * @param atchFileVO
	 * @return
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 조회하기
	 * @param atchFileVO
	 * @return
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO);
}
