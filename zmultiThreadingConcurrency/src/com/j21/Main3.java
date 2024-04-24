package com.j21;


public class Main3 {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(System.currentTimeMillis());
		for(int i=0; i <100_00; i++) 
		{
			Thread t1 = new Thread(() -> System.out.print(""));		
			t1.setDaemon(true);
			t1.start();
			//t1.join();
		}
		System.out.println(System.currentTimeMillis());
		
		MainThread m1 = new MainThread();
		m1.run();
	}

}
