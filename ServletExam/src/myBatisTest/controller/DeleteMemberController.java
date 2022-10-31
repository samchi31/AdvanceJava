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

@WebServlet("/member/delete.do")
public class DeleteMemberController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 값 조회
		String memId = req.getParameter("memId");

		// 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		int cnt = memService.removeMember(memId);

		// 리다이렉트
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
