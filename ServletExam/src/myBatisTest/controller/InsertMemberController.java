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

@WebServlet("/member/insert.do")
public class InsertMemberController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/insertForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.setCharacterEncoding("utf-8");
		
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
		
		int cnt = memService.registMember(mv);
		String msg = "실패";
		if(cnt > 0 ) {
			msg="성공";
		} 
		// req 객체는 리다이렉트로 인해서 insert 후 소멸함 list.do 까지 정보를 유지하기위해 세션사용
		req.getSession().setAttribute("msg", msg);
		
		// 포워딩 시 리스트 페이지 새로고침하면 500 에러남
//		req.getRequestDispatcher("/member/list.do").forward(req, resp);	// 서블릿 컨텍스트 생략
		resp.sendRedirect(req.getContextPath()+ "/member/list.do");	// 서블릿 컨텍스트를 적어줘야함 (클라이언트 입장)
	}
}
