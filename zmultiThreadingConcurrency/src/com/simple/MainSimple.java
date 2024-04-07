package com.simple;

public class MainSimple {
	
	public static void main(String[] args) 
	{
		Thread t1 = new Thread(new SimpleThread());
		t1.start();
	}
}