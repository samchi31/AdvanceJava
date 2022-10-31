package javaNetwork.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost",7777);
			System.out.println("서버 연결 완료...");
			
			Sender sender = new Sender(socket);
			sender.start();
			
			Receiver receiver = new Receiver(socket);
			receiver.start();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
