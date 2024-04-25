package com.practice;

class ProducerSingleton implements Runnable
{

	@Override
	public void run() 
	{
		int count = 0;
		while(true) 
		{			
			Singleton singleton = Singleton.getInstance();			
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			if(count == 2) break;
			count++;
		}		
	}
	
}

class ConsumerSingleton implements Runnable
{
	@Override
	public void run() 
	{
		try { Thread.sleep(25000); } catch (InterruptedException e) { e.printStackTrace(); }
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton);
		
	}
	
}

public class SingletonUse 
{
	
	public static void main(String[] args) 
	{
		new Thread(new ProducerSingleton()).start();	
		
		new Thread(new ConsumerSingleton()).start();
		
	}

}
