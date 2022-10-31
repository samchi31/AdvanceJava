package servletTest;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T07ServletContextTest extends HttpServlet {
	/*
	 * 서블릿 컨텍스트 객체에 대하여
	 * 
	 * 1. 서블릿 프로그램이 컨테이너와 정보를 주고 받기 위한 메서드를 제공함 ex) 파일의 MIME 타입정보 가져오기, 요청정보 보내기, 로깅
	 * 등
	 * 
	 * 2. 웹 애플리케이션당 1개씩 생성됨
	 * 
	 * 3. 서블릿 컨텍스트 객체는 서블릿이 초기화 될때 ServletConfig 객체를 통해서 얻을 수 있다
	 * 
	 * 전역변수 느낌..?
	 * 
	 * 세션 - 브라우저 접속 동안 컨텍스트 - 서버(톰캣) 서비스 동안
	 */
	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		System.out.println(config.getServletContext());
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext ctx = req.getServletContext();
//		ctx = getServletContext();

		System.out.println("서블릿 컨텍스트의 기본경로 : " + ctx.getContextPath());
		System.out.println("서버 정보 : " + ctx.getServerInfo());
		System.out.println("서블릿 API의 메이저 버전정보 : " + ctx.getMajorVersion());
		System.out.println("서블릿 API의 마이너 버전정보 : " + ctx.getMinorVersion());
		System.out.println("배포기술자에 기술된 컨텍스트명 : " + ctx.getServletContextName());	// 배포기술자 = web.xml의 displayname
		System.out.println("리소스 경로 목록 : " + ctx.getResourcePaths("/"));
		System.out.println("파일에 대한 MIME타입 정보 : " + ctx.getMimeType("abc.mp3"));
		System.out.println("파일 시스템 상의 실제 경로 : " + ctx.getRealPath("/"));

		// 속성 값 설정
		ctx.setAttribute("attr1", "속성1");

		// 속성 값 변경
		ctx.setAttribute("attr1", "속성2");

		// 속성 값 가져오기
		System.out.println("attr1의 속성값 : " + ctx.getAttribute("attr1"));
		
		// 속성 값 지우기
		ctx.removeAttribute("attr1");
		
		// 로깅작업
		ctx.log("서블릿 컨텍스트를 이용한 로깅작업 중...");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
