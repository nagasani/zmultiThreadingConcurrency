package com.j21;

class Count
{
	int count;
	
	public synchronized void increment()
	{	
		count++;
	}
}

public class SyncDemo 
{	
	public static void main(String[] args) throws Exception
	{
		Count ct = new Count();
	
		Thread t1 = new Thread((() -> {for(int i=0; i < 1000; i++) {ct.increment();}}));
		Thread t2 = new Thread(() -> {for(int i=0; i < 1000; i++) {ct.increment();}});
		
		t1.start();		
		t2.start();
		t1.join();
		t2.join();
		
		System.out.println(ct.count);
	}
}



