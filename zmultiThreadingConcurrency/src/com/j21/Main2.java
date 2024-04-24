package com.j21;


public class Main2 {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(System.currentTimeMillis());
		
		
		MainThread m1 = new MainThread();
		m1.run();
	
		
		System.out.println(System.currentTimeMillis());
		
		
	}

}
