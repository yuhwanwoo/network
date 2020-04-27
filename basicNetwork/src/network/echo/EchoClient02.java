package network.echo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient02 {

	public static void main(String[] args) {
		
		
		Socket socket=null;
		BufferedReader in=null;
		PrintWriter out=null;
		BufferedReader keyin=null;
		try {
			socket = new Socket("70.12.115.69", 12345);
			System.out.println("서버접속완료..."+socket);
			
			
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

			//생성할때 autoflush옵션을 설정
			out =new PrintWriter(socket.getOutputStream(),true);
			
			//키보드로 입력한 데이터를 읽기 위한 스트림 생성
			keyin=new BufferedReader(new InputStreamReader(System.in));
			
			//*****통신시작******
			String sendMsg="";//서버로 보낼 메시지
			String resMsg="";//서버에서 받는 메시지
			//1. 클라이언트 <- 서버
			resMsg=in.readLine();
			System.out.println("서버에서 보내 온 메시지>>"+resMsg);
			while((sendMsg=keyin.readLine())!=null) {
				//2. 클라이언트 -> 서버(키보드로 입력하는 데이터를 서버로 전송)
				out.println(sendMsg);
				//3. 클라이언트 <- 서버
				resMsg=in.readLine();
				System.out.println("서버가 보내는 메시지>>"+resMsg);
			}
			
			
			/*내가 푼것
			 * 
			 * System.out.println("처음 메시지 :"+resMsg);
			while((sendMsg=keyin.readLine())!=null) {
				//1. 클라이언트 -> 서버(키보드로 입력하는 데이터를 서버로 전송)
				out.println(sendMsg);
				//2. 클라이언트 <- 서버
				resMsg=in.readLine();
				System.out.println("서버가 보내는 메시지:"+resMsg);
			}*/
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
