package chat.step06;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientChatView extends JFrame {

	 JPanel contentPane; 
	 JTextField txtinput;
	 JTextArea taChat;
	 JButton btnsend;
	 JList lstconnect;
	 
	 String ip;
	 int port;
	 String nickname;
	 Socket socket;
	 
	//1. ========================서버와 통신하기 위한 입출력 스트림 변수선언=================
		 InputStream is;
		 InputStreamReader ir;
		 BufferedReader br;
		 
		 OutputStream os;
		 PrintWriter pw;
		 //==========================================================================
	/**
	 * Launch the application.
	 */
	 /*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientChatView frame = new ClientChatView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	 
	public ClientChatView(String ip,int port,String nickname) {
		this.ip=ip;
		this.port=port;
		this.nickname=nickname;
		//===========================================================
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		taChat = new JTextArea();
		taChat.setBounds(12, 10, 501, 375);
		contentPane.add(taChat);
		
		txtinput = new JTextField();
		txtinput.setBounds(12, 395, 378, 35);
		contentPane.add(txtinput);
		txtinput.setColumns(10);
		
		btnsend = new JButton("\uC11C\uBC84\uB85C\uC804\uC1A1");
		btnsend.setFont(new Font("HY견고딕", Font.BOLD, 14));
		btnsend.setBounds(402, 395, 109, 35);
		contentPane.add(btnsend);
		
		JLabel lblNewLabel = new JLabel("\uC811\uC18D\uC790:");
		lblNewLabel.setFont(new Font("HY견고딕", Font.BOLD, 14));
		lblNewLabel.setBounds(519, 10, 120, 35);
		contentPane.add(lblNewLabel);
		
		lstconnect = new JList();
		lstconnect.setBounds(525, 47, 205, 108);
		contentPane.add(lstconnect);
		
		setVisible(true);//화면에 JFrame을 보이도록 설정
		
		//이벤트 연결하기
		ClientChatListener listener=new ClientChatListener(this);
		txtinput.addActionListener(listener);
		btnsend.addActionListener(listener);
		
		connectServer();//서버에 접속
	}
	
	
	public void connectServer() {
		try {
			socket=new Socket(ip,port);
			
			
			if(socket!=null) {
				ioWork();
			}
			//서버한테 nickname보내기
			sendMsg(nickname);
			
			//1.=====서버가 보내오는 데이터를 읽는 부분을 쓰레드로 처리==================
			Thread receiveThread=new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						String msg="";
						try {
							msg=br.readLine();
							System.out.println("서버가 전달한 메시지>>"+msg);
						}catch(IOException e) {
							e.printStackTrace();
						}
					}
					
				}
			});
			receiveThread.start();
			String msg=br.readLine();
			//taChat.append(msg+"\n");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
		public void ioWork() {
			try {
				is=socket.getInputStream();
				ir=new InputStreamReader(is);
				br=new BufferedReader(ir);
				
				os=socket.getOutputStream();
				pw=new PrintWriter(os,true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		public void sendMsg(String message) {
			System.out.println("클라이언트가 서버에게 메시지 전송하기"+message);
			pw.println(message);
		}
}
