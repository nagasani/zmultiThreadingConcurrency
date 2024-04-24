package com.j21;

public class RunMainAndChildThread {

	public static void main(String[] args) {
		
		MainAndChildThread m = new MainAndChildThread();
		m.setDaemon(true);
		m.setPriority(1);
		m.start();
		
		for(int i= 0; i <10; i++) 
		{
			System.out.println("In Main3 Thread");
		}
		
		System.out.println("Deamon Thread : "+m.isDaemon());
		
		while(m.isDaemon()) 
		{
			m.setDaemon(true);
		}
		
		System.out.println("Deamon Thread : "+m.isDaemon());
	}
	
}
