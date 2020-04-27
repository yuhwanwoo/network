package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyNetServer01 {

	public static void main(String[] args) {

		try {
			//클라이언트와 통신할 수 있도록 준비
			ServerSocket server =new ServerSocket(12345);
			System.out.println("서버준비완료....클라이언트의 접속을 기다림");
			//클라이언트가 접속할때까지 대기
			//클라이언트가 접속하면 접속한 클라이언트의 소켓객체를 리턴
			Socket client=server.accept();
			InetAddress clientInfo=client.getInetAddress();
			
			System.out.println("접속한 클라이언트:"+clientInfo.getHostAddress());
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
