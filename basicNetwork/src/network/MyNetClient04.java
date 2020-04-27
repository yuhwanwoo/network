package network;

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

public class MyNetClient04 {

	public static void main(String[] args) {
		
		
		Socket socket=null;
		BufferedReader in=null;
		PrintWriter out=null;
		try {
			socket = new Socket("70.12.115.69", 12345);
			System.out.println("서버접속완료..."+socket);
			
			
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

			//생성할때 autoflush옵션을 설정
			out =new PrintWriter(socket.getOutputStream(),true);
			
			//*****통신시작******
			
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
