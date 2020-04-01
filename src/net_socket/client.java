package net_socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
	
	private Socket to=null;
	private PrintWriter a=null;
	private BufferedReader d=null;
	public client() throws UnknownHostException, IOException
	{
		to=new Socket("127.0.0.1",8080);
		a=new PrintWriter(to.getOutputStream(),true);
		d= new BufferedReader(new InputStreamReader(to.getInputStream()));
		
		new Thread(new sthread()).start();
		while(true)
		{
			Scanner sc=new Scanner(System.in);
			a.println(sc.nextLine());
		}
	}
	
	void get() throws IOException
	{
//		 byte[] buf = new byte[1024];
//         int len = 0;
//         while ((len = to.getInputStream().read(buf)) != -1) {
//             System.out.println("收到服务端口的语句：" + new String(buf, 0, len));
//         }
		String rs = d.readLine();
		System.out.println(rs);
	}
	
	class sthread implements Runnable
	{
		@Override
		public void run() {
			while(true)
			{
				try {
					get();
				} catch (IOException e) {
					e.printStackTrace();
					try {
						to.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		}
		
	}
	
	public static void main(String args[])
	{
		try {
			new client();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
