package myBatisTest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myBatisTest.MemberVO;
import myBatisTest.common.AtchFileVO;
import myBatisTest.common.service.AtchFileServiceImpl;
import myBatisTest.common.service.IAtchFileService;
import myBatisTest.service.IMemberService;
import myBatisTest.service.MemberServiceImpl;

@MultipartConfig
@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파라미터 값 조회
		String memId = req.getParameter("memId");

		// 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		MemberVO mv = memService.getMember(memId);
		
		if(mv.getAtchFileId()>0) {	// 첨부파일이 존재하면...
			// 첨부파일 목록 조회
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			AtchFileVO atchFileVO = new AtchFileVO();
			atchFileVO.setAtchFileId(mv.getAtchFileId());
			
			List<AtchFileVO> atchFileList = fileService.getAtchFileList(atchFileVO);
			req.setAttribute("atchFileList", atchFileList);
		}

		req.setAttribute("mv", mv);

		// 업데이트 폼화면으로 이동
		req.getRequestDispatcher("/WEB-INF/views/member/updateForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 값 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		long atchFileId = Long.parseLong(req.getParameter("atchFileId"));

		// 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		AtchFileVO atchFileVO = new AtchFileVO();
		//첨부파일목록 저장(공통기능)
		atchFileVO = fileService.saveAtchFileList(req);
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		if(atchFileVO == null) {	// 첨부파일 그대로인 경우
			mv.setAtchFileId(atchFileId);
		} else {								// 첨부파일 변경이 일어난 경우
			mv.setAtchFileId(atchFileVO.getAtchFileId());
		}
		
		
		int cnt = memService.modifyMember(mv);
		String msg = "실패";
		if(cnt > 0 ) {
			msg="성공";
		} 
		// req 객체는 리다이렉트로 인해서 insert 후 소멸함 list.do 까지 정보를 유지하기위해 세션사용
		req.getSession().setAttribute("msg", msg);
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
	}

}
