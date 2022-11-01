package myBatisTest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myBatisTest.MemberVO;
import myBatisTest.service.IMemberService;
import myBatisTest.service.MemberServiceImpl;

@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파라미터 값 조회
		String memId = req.getParameter("memId");

		// 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		MemberVO mv = memService.getMember(memId);

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

		// 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);

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
