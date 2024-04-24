package com.mul.producer_consumer;


class Q 
{
	volatile int i;
	
	boolean valueSet = false;
	
	public synchronized void set(int i) 
	{
		while(!valueSet) 
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Set : "+i);
		this.i=i;
		valueSet = false;
		notifyAll();		
	}
	
	public synchronized void get()  
	{
		while(valueSet) 
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Get : "+i);
		valueSet = true;
		notifyAll();
	}
}

class Produ implements Runnable
{
	Q q;
	
	/**
	 * @param q
	 */
	public Produ(Q q) 
	{				
		this.q = q;
	}

	@Override
	public void run() 
	{
		int i=0;
		while(true) 
		{
			q.set(i++);
			try {Thread.sleep(1000);} catch (Exception e) {}
		}		
	}
	
}

class Consu implements Runnable
{
	Q q;
	
	/**
	 * @param q
	 */
	public Consu(Q q) 
	{		
		this.q = q;
	}

	@Override
	public void run() 
	{
		while(true) 
		{
			q.get();
			try {Thread.sleep(1000);} catch (Exception e) {}
		}		
	}
}

public class InterThreadComm 
{
	public static void main(String[] args) {
		Q q= new Q();
		Thread p = new Thread(new Produ(q));
		Thread c = new Thread(new Consu(q));
		p.start();
		c.start();
	}
}
