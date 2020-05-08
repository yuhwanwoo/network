package chat.step09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

public class User extends Thread{
	//ChatServerView에서 넘겨받을 데이터
	Socket client;
	ChatServerView serverView;
	InputStream is;
	InputStreamReader ir;
	BufferedReader br;

	OutputStream os;
	PrintWriter pw;
	String nickname;
	

	StringTokenizer st;
	//===================================================
	Vector<User> userlist;
	public User() {
		
	}

	public User(Socket client, ChatServerView serverView,Vector<User> userlist) {
		super();
		this.client = client;
		this.serverView = serverView;
		this.userlist=userlist;
		ioWork();
	}
	//접속한 클라이언트와 서버가 통신할 수 있도록 스트림객체 생성
	public void ioWork() {//처음 접속했을 때 한 번 실행되는 메소드
		try {
			is = client.getInputStream();
			ir = new InputStreamReader(is);
			br = new BufferedReader(ir);

			os = client.getOutputStream();
			pw = new PrintWriter(os, true);
			
			nickname=br.readLine();
			System.out.println("nickname:"+nickname);
			serverView.taclientlist.append("****************"+nickname+"님이 입장************\n");
		
 
			broadCast("new/"+nickname);


			int size=userlist.size();//기존 접속자 인원수
			for(int i=0;i<size;i++) {
				User user=userlist.get(i);
				sendMsg("old/"+user.nickname);//이미 접속한 사용자들한테 안내 - 새로 접속한 사용자의 nickname을 보낸다.
			}
			
			userlist.add(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void filteringMsg(String msg) {
		System.out.println("서버가 받은 클라이언트의 메시지:"+msg);
		st=new StringTokenizer(msg,"/");
		String protocol=st.nextToken();
		if(protocol.equals("chatting")) {
			String message=st.nextToken();
			String nickname=st.nextToken();
			//클라이언트가 메시지를 보내면 연결되어 있는 모든 다른 클라이언트에게 메시지를 전송
			broadCast("chatting/"+message+"/"+nickname);
		}
	}
	
	
	private void broadCast(String msg) {
		int size=userlist.size(); //기존 접속자 인원수
		for(int i=0;i<size;i++) {
			User user = userlist.get(i);
			user.sendMsg(msg);//이미 접속한 사용자들한테 안내 - 새로 접속한 사용자의 nickname을 보낸다.
		}
	}
	
	public void sendMsg(String message) {
		pw.println(message);
		
	}
	
	//===================================================================
	//쓰레드로 처리할 작업 - 접속한 클라이언트가 전송하는 메시지를 계속 받아서 서버창에 출력 
	public void run() {
		while(true) {
			try {
				String msg=br.readLine();
				serverView.taclientlist.append(nickname+"이 전송한 메시지:"+msg+"\n");
			
				filteringMsg(msg);
				//================================================================
			} catch (IOException e) {
				//3. 클라이언트 접속이 끊어지면 처리=============================
				serverView.taclientlist.append("클라이언트의 접속이 끊어짐\n");
				try {
					is.close();
					ir.close();
					br.close();
					os.close();
					pw.close();
					client.close();
					userlist.remove(this);//클라이언트 목록에서 제거
					broadCast("out/+nickname");//다른 클라이언트에게 알려주는 작업
					//==============================================================
				}catch(IOException e1) {
					
				}
				break;
			}
		}
	}

}
