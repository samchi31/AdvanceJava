package javaNetwork.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSender {
	private DatagramSocket ds;
	private DatagramPacket dp;

	private InetAddress receiverAddr;
	private int port; // 데이터를 보낼 때 사용할 포트번호
	private File file;

	public UdpFileSender(String receiverIp, int port) {
		try {
			ds = new DatagramSocket();
			this.port = port;
			receiverAddr = InetAddress.getByName(receiverIp);
			file = new File("d:/D_Other/br5.jpg");

			if (!file.exists()) {
				System.out.println("파일이 존재하지 않음");
				System.exit(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() throws Exception {
		long fileSize = file.length();
		long totalReadBytes = 0;

		long startTime = System.currentTimeMillis();

		try {
			// 전송 시작을 알려주기 위한 문자열 전송
			sendData("start".getBytes());

			// 파일명 전송
			sendData(file.getName().getBytes());

			// 총 파일 크기 정보를 전송
			sendData(String.valueOf(fileSize).getBytes());

			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1000];
			while (true) {
				// 패킷 전송간의 간격을 주기 위해
				Thread.sleep(10);
				int readBytes = fis.read(buffer, 0, buffer.length);
				if (readBytes == -1) { // 다 읽은 경우...
					break;
				}

				// 읽은 파일 내용 전송
				sendData(buffer, readBytes);

				totalReadBytes += readBytes;
				System.out.println("진행상태:" + totalReadBytes + "/" + fileSize + " Byte(s) ("
						+ (totalReadBytes * 100 / fileSize) + "%)");
			}
			long endTime = System.currentTimeMillis();
			long diff = endTime - startTime;
			double transferSpeed = fileSize / diff;

			System.out.println("걸린 시간 : " + diff + "(ms)");
			System.out.println("평균 전송속도 : " + transferSpeed + " Bytes/ms");

			System.out.println("전송완료...");

			fis.close();
			ds.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 바이트 배열 데이터 전송하기
	 * 
	 * @param data
	 * @param length
	 * @throws IOException
	 */
	public void sendData(byte[] data, int length) throws IOException {
		dp = new DatagramPacket(data, length, receiverAddr, port);
		ds.send(dp);
	}

	public void sendData(byte[] data) throws IOException {
		sendData(data, data.length);
	}

	public static void main(String[] args) throws Exception {
		new UdpFileSender("192.168.35.51", 8888).start();
	}
}
