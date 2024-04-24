package com.j21;

public class Main5 {
	
	public static void main(String[] args) throws InterruptedException 
	{
		
		
		Thread t1 = new Thread(
					() ->
					{
						while(true) 
						{
							try 
							{
								if (Thread.currentThread().isDaemon()) 
								{
				                    System.out.println("This is a daemon thread.");
				                } 
								else 
				                {
				                    System.out.println("This is not a daemon thread.");
				                }						
								Thread.sleep(1000);
								//Thread.currentThread().setDaemon(false);
							} 
							catch (InterruptedException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}						
					}
				);
		t1.setName("T1");
		t1.setDaemon(true);
		t1.start();		
		Thread.sleep(5000);		
		System.out.println("Finished.");		
	}

}
