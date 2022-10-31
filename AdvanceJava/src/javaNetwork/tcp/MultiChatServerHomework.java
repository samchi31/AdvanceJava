package javaNetwork.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MultiChatServerHomework {
	// 대화명 , 클라이언트의 Socket을 저장하기 위한 Map 변수 선언
	private Map<String, Socket> clients;

	// 생성자
	public MultiChatServerHomework() {
		// 동기화 처리가 가능하도록 Map객체 생성
		clients = Collections.synchronizedMap(new HashMap<>());
	}

	// 서버 시작
	public void serverStart() {
		Socket socket = null;
		try (ServerSocket serverSocket = new ServerSocket(7777)) { // close 자동
			System.out.println("서버 시작..");

			while (true) {
				// 클라이언트 접속 대기
				socket = serverSocket.accept();
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속");

				// 메시지 전송 처리를 하는 스레드 생성 및 실행
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 대화방 전체 유저에게 안내 메세지를 전송하는 메서드
	 * 
	 * @param msg
	 */
	public void sendMessage(String msg) {
		// Map에 저장된 유저 리스트 추출(key 값 구하기)
		Iterator<String> it = clients.keySet().iterator();
		while (it.hasNext()) {
			try {
				String name = it.next();
				// 대화명에 해당하는 Socket 객체에서 OutputStream꺼내기
				DataOutputStream dos;
				dos = new DataOutputStream(clients.get(name).getOutputStream());
				dos.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 대화방 전체 유저에게 대화 메세지를 전송하는 메서드
	 * 
	 * @param msg
	 * @param from
	 */
	public void sendMessage(String msg, String from) {
		String[] msgs = msg.split(" ");
		if (msgs.length > 0 && msgs[0].equals("/w")) {
			sendPrivate(msg, from, msgs[1]);
		} else {
			sendMessage("[" + from + "]:" + msg);
		}
	}

	public void sendPrivate(String msg, String from, String to) {
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(clients.get(to).getOutputStream());
			dos.writeUTF("[" + from + "]:" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서버에서 클라이언트 메시지를 전송할 Thread 클래스를 Inner클래스로 정의하면
	// 부모(Outer) 클래스의 멤버들을 직접 사용할 수 있다.
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream dis;
		private String name;

		public ServerReceiver(Socket socket) {
			this.socket = socket;

			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				// 서버에서 클라이언트가 보내는 최초의메세지(대화명)을 수신해야한다
				name = dis.readUTF();

				// 대화명을 받아서 다른 모든 클라이언트에게 대화방 참여 메시지 보낸다
				sendMessage("#" + name + "님이 입장");

				// 대화명과 소켓객체를 Map에 저장
				clients.put(name, socket);

				System.out.println("현재 서버 접속자 수는" + clients.size() + "명 입니다");

				// 이 이후의 메시지 처리는 반복문으로
				// 한 클라이언트의 메시지를 모든 클라이언트에게 전송
				while (dis != null) {
					sendMessage(dis.readUTF(), name);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 클라이언트의 접속이 종료되었을 때
				sendMessage(name + "님이 퇴장");

				// Map에서 삭제
				clients.remove(name);

				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속 종료");
				System.out.println("현재 서버 접속자 수는" + clients.size() + "명 입니다");

			}
		}
	}

	public static void main(String[] args) {
		new MultiChatServerHomework().serverStart();
	}
}
