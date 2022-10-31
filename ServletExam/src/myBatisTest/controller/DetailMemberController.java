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

@WebServlet("/member/detail.do")
public class DetailMemberController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 값 조회
		String memId = req.getParameter("memId");
		
		// 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		MemberVO mv = memService.getMember(memId);
		
		req.setAttribute("mv", mv);
		
		// jsp에게 포워딩
		req.getRequestDispatcher("/WEB-INF/views/member/detail.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
