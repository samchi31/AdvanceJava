package servletTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T12ImageServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("image/jpeg"); 		// 컨텐츠 타입 설정
//		resp.setContentType("text/html");
		resp.setContentType("video/mp4");
		ServletOutputStream out = resp.getOutputStream();
		
//		FileInputStream fis = new FileInputStream("d:/D_Other/Tulips.jpg");
		FileInputStream fis = new FileInputStream("C:\\Users\\PC-07\\eclipse-workspace\\Webprogramming\\vscode_web\\videos\\Spider-Man Into The Spiderverse ‘What’s Up Danger Song’ Movie Clip (2018) HD.mp4");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		int data = 0 ;
		
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
	}
}
