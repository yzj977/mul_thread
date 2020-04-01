package mul_thread;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Swingandthread extends JFrame{
	private static Thread t;
	private int count=0;
	
	public Swingandthread()
	{
		setBounds(300,200,250,100);
		JLabel jl=new JLabel();
		Icon ic=new ImageIcon("C:\\Users\\Lenovo\\Desktop\\login\\pwd.png");
		jl.setIcon(ic);
		jl.setHorizontalAlignment(SwingConstants.LEFT);
		jl.setBounds(10,10,200,50);
		jl.setOpaque(true);
		t=new Thread(new Runnable(){
			public void run() {
				while(count<=200)
				{
					jl.setBounds(count, 10, 200, 50);
					
					try {
						Thread.sleep(1000);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					count+=10;
					if(count==200)
					{
						count=10;
					}
				}
			}
		});
		t.start();
		
		getContentPane().setLayout(null);
		getContentPane().add(jl);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Swingandthread();
	}

}
