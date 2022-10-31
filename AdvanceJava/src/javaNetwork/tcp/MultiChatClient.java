package javaNetwork.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	// 클라이언트 시작
	public void ClientStart() {
		Socket socket = null;
		try {
			socket = new Socket("192.168.35.51", 7777);
			System.out.println("서버에 연결되었습니다");
			// 송신용 스레드 생성
			new ClientSender(socket).start();
			// 수신용 스레드 생성
			new ClientReceiver(socket).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 메시지를 전송하는 스레드 클래스
	class ClientSender extends Thread {
		private DataOutputStream dos;
		private Scanner scan = new Scanner(System.in);

		public ClientSender(Socket socket) {
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				if (dos != null) {
					// 시작하자마자 자신의 대화명을 서버로 전송
					System.out.println("대화명 >> ");
					dos.writeUTF(scan.nextLine());
				}

				while (dos != null) {
					// 키보드로 입력받은 메시지를 서버로 전송
					dos.writeUTF(scan.nextLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// 메시지를 수신하는 스레드 클래스
	class ClientReceiver extends Thread {
		private DataInputStream dis;

		public ClientReceiver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while (dis != null) {
					// 수신 메시지 출력
					System.out.println(dis.readUTF());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static void main(String[] args) {
		new MultiChatClient().ClientStart();
	}
}
