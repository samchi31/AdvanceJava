package javaNetwork.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.stream.FileImageOutputStream;

public class TcpFileClient {
	// 클라이언트는 서버에 접속하여 서버가 보내주는 파일을 저장한다
	private Socket socket;
	private FileOutputStream fos;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public void clientStart() {
		File file = new File("d:/D_Other/br5.jpg");
		try {
			socket = new Socket("192.168.35.90",7777);
			
			// 소켓 접속이 성공하면 받고 싶은 파일명을 보낸다
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(file.getName());
			
			dis = new DataInputStream(socket.getInputStream());
			
			String resultMsg = dis.readUTF();
			if(resultMsg.equals("OK")) {
				fos = new FileOutputStream(file);
				BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				
				int data = 0;
				while((data=bis.read()) != -1) {
					bos.write(data);
				}
				bis.close();
				bos.close();
				System.out.println("file 다운로드 완료...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
}
