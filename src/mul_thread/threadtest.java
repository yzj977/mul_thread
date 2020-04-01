package mul_thread;

public class threadtest extends Thread{

	private int count=10;
	
	threadtest()
	{
		new test().run();
	}
	
	public class test implements Runnable
	{
		public void run()
		{
			while(true)
			{
				System.out.print(count+" ");
				--count;
				if(count==0)
				{
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new threadtest();
	}

}
