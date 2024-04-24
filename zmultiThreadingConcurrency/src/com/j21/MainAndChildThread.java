package com.j21;

public class MainAndChildThread extends Thread {
	
	public void run() {
		for(int i =0; i <10 ;i++) 
		{
			System.out.println("In child Thread");
		}
	}

}
