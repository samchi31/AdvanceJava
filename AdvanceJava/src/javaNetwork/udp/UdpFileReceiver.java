package javaNetwork.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpFileReceiver {
	private DatagramSocket ds;
	private DatagramPacket dp;

	private byte[] buffer;

	public UdpFileReceiver(int port) {
		try {
			// 데이터 수신을 위한 포트번호 설정
			ds = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 데이터 수신하기
	 * 
	 * @return
	 * @throws IOException
	 */
	public byte[] receiveData() throws IOException {
		buffer = new byte[1000];
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);

		return dp.getData();
	}

	public void start() throws IOException {
		long fileSize = 0;
		long totalReadBytes = 0;

		int readBytes = 0;
		System.out.println("파일 수신 대기 중...");

		String str = new String(receiveData()).trim();

		if (str.equals("start")) {
			// 전송 파일명 받기
			str = new String(receiveData()).trim();

			FileOutputStream fos = new FileOutputStream("d:/D_Other/" + str);

			// 전송 파일 크기 (bytes) 받기
			str = new String(receiveData()).trim();
			fileSize = Long.parseLong(str);

			long startTime = System.currentTimeMillis();

			while (true) {
				byte[] data = receiveData();
				readBytes = dp.getLength(); // 받은 데이터 크기
				fos.write(data, 0, readBytes);

				totalReadBytes += readBytes;
				System.out.println("진행상태:" + totalReadBytes + "/" + fileSize + " Byte(s) ("
						+ (totalReadBytes * 100 / fileSize) + "%)");

				if (totalReadBytes >= fileSize) {
					break;
				}
			}
			long endTime = System.currentTimeMillis();
			long diff = endTime - startTime;
			double transferSpeed = fileSize / diff;

			System.out.println("걸린 시간 : " + diff + "(ms)");
			System.out.println("평균 수신속도 : " + transferSpeed + " Bytes/ms");

			System.out.println("수신완료...");
		}
	}
	public static void main(String[] args) throws IOException {
		new UdpFileReceiver(8888).start();
	}
}
