package mul_thread;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class sleepmethodtest extends JFrame{

	private Thread t;
	private static Color color[]= {Color.black,Color.red,Color.orange,
			Color.yellow,Color.green,Color.blue,Color.pink,Color.lightGray,Color.white};
	
	private static final Random rand=new Random();
	private static Color getC()
	{
		return color[rand.nextInt(color.length)];	//产生不大于length的值
	}
	
	public sleepmethodtest()
	{
		t=new Thread(new Runnable() {
			int x=30;
			int y=50;
			public void run()
			{
				while(true)
				{
					try {
						Thread.sleep(1000);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					Graphics graphics=getGraphics();
					graphics.setColor(getC());
					graphics.drawLine(x, y, 100, y);
					y=y+3;
					if(y>80)
					{
						y=50;
					}
				}
			}
		});
		t.start();
		
		setSize(100,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new sleepmethodtest();
		
	}
}
