package javaXMLTest;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Homework {
	public void parse() throws Exception {
		String serviceKey = "m3zTXtgIgwkhr2WboRC5nIwTq5fQN7q8T2ZteWypCKCrIdm2eM4fUcWZ5PKNFiONOv6Xmj%2FAYqckvEgAmB6oEA%3D%3D";
		String foodId = "13";
		String pageSize = "10";
		String startPage = "0";
		URL url = new URL("http://apis.data.go.kr/6460000/jnFood/getNdfoodMenuList?serviceKey=" + serviceKey
				+ "&foodId=" + foodId + "&pageSize=" + pageSize + "&startPage=" + startPage);
//		URL url = new URL("http://apis.data.go.kr/6460000/jnFood/getNdfoodList?serviceKey="+serviceKey+"&menuCd=02&foodNm=독천&pageSize=10&startPage=0");
//		URL url = new URL("http://apis.data.go.kr/6460000/jnFood/getNdfoodView?serviceKey="+serviceKey+"&foodId=13&pageSize=10&startPage=0");

		// XML 문서를 생성하기 위한 DocumentBuilder 객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = dbf.newDocumentBuilder();

		// Document 객체 생성
		Document document = builder.parse(url.toString());

		// ROOT 엘리먼트 가져오기
		Element root = document.getDocumentElement();
		System.out.println("루트엘리먼트 : " + root.getTagName());
	}

	public static void main(String[] args) throws Exception {
		new Homework().parse();
	}
}
