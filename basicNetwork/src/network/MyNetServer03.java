package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class MyNetServer03 {

	public static void main(String[] args) {
		Socket client=null;
		InputStream is=null;//클라이언트와 input할 수 있는 스트림
		DataInputStream dis=null;//최종적으로 클라이언트와 DataInputStream를 이용해서
								//클라이언트로 부터 전송되는 데이터를 읽기
		OutputStream os=null;//클라이언트와 output할 수 있는 스트림
		DataOutputStream dos=null;//
		Random ran=new Random();
		try {
			// 클라이언트와 통신할 수 있도록 준비
			ServerSocket server = new ServerSocket(12344);
			System.out.println("서버준비완료....클라이언트의 접속을 기다림");
	
			//여러 클라이언트에게 서비스를 하기위해서 무한루프
			while (true) {

				client = server.accept();
				InetAddress clientInfo = client.getInetAddress();
				System.out.println("접속한 클라이언트:" + clientInfo.getHostAddress());
				//클라이언트와 통신하기 위한 Input/output Stream객체를 소켓으로부터 구한다.
				//inputStream 얻기
				is=client.getInputStream();
				dis=new DataInputStream(is);
				
				//OutputStream얻기
				os=client.getOutputStream();
				dos=new DataOutputStream(os);
				
				//1. 서버 -> 클라이언트
				dos.writeUTF("안녕하세요 클라이언트님");
				//2. 서버 -> 클라이언트
				int ranNum=ran.nextInt(8)+2;
				dos.writeInt(ranNum);
				
				//3. 서버 <- 클라이언트
				String data=dis.readUTF();
				System.out.println(data);
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
