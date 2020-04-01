package net_socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class server {
	private ServerSocket my=null;
	private List<getpost> clients = new ArrayList<getpost>();
	server()
	{
		Socket to=null;
		try {
			my=new ServerSocket(8080);
			while(true)
			{
				try {
					to=my.accept();
					getpost first=new getpost(to);
					new Thread(first).start();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class getpost implements Runnable
	{
		PrintWriter a=null;
		BufferedReader d=null;
		Socket s=null;
		String name=null;
		
		getpost(Socket tmp) throws IOException
		{
			this.s=tmp;
			a = new PrintWriter(s.getOutputStream(), true);
			d= new BufferedReader(new InputStreamReader(s.getInputStream()));
			Random r=new Random();
			name=((Integer)r.nextInt(100)).toString();
			a.println(name+"上线了！");
			clients.add(this);
		}
		
		void post() throws IOException
		{
			String str=null;
			str=d.readLine();
//			System.out.println(str);

			for(getpost temp:clients)
			{
				if(temp!=this)
					temp.a.println(this.name+":"+str);
			}
		}
		
		@Override
		public void run() {
			while(true)
			{
				try{
					post();
				}catch(SocketException e){
					System.out.println("link reset.");
					try {
						s.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//e.printStackTrace();
					break;
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new server();
	}

}
