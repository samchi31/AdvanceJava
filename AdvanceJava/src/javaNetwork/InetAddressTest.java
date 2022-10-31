package javaNetwork;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		
		// InetAddress 클래스 => IP 주소를 다루기 위한 클래스
		
		// getByName() 메서드는 www.naver.com 또는 000-PC 등과 같은 머신 이름이나 
		// IP 주소를 통해 유효한 InetAddress 객체를 제공한다
		// 주소 자체의 유효성 체크가 이루어짐
		
		// 네이버의 IP 정보 가져오기
		InetAddress naverIp =InetAddress.getByName("www.naver.com");
		System.out.println("Host name : " + naverIp.getHostName());
		System.out.println("Host Addr : " + naverIp.getHostAddress());
		System.out.println();
		
		// 자기 자신 컴퓨터의 IP 정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터 host name : " + localIp.getHostName());
		System.out.println("내 컴퓨터 host addr : " + localIp.getHostAddress());
		System.out.println();
		
		// IP 주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		for (InetAddress iAddr : naverIps) {
			System.out.println(iAddr.toString());
		}
	}
}
