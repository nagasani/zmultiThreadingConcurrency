package com.mul.producer_consumer;

class Buff
{	
	volatile int i;
	volatile boolean valueSet = false;
	
	public synchronized void put(int i) 
	{
		while(valueSet) 
		{
			try{ wait(); } catch (InterruptedException e) {}
		}		
		System.err.println("Put : "+i);
		this.i=i;
		valueSet = true;
		notify();
	}
	
	public synchronized void get() 
	{	
		while(!valueSet) 
		{
			try{ wait(); } catch (InterruptedException e) {}
		}
		System.err.println("Get : "+i);
		valueSet = false;
		notify();
	}	
}

class Produ2 implements Runnable
{
	Buff buff;
		
	public Produ2(Buff buff) { this.buff = buff; }

	@Override
	public void run() 
	{
		int i = 0;
		while(true) 
		{
			buff.put(i++);
			try { Thread.sleep(1000);} catch (InterruptedException e) {}
		}		
	}
}

class Consu2 implements Runnable
{
	Buff buff;

	public Consu2(Buff buff) { this.buff = buff; }
	
	@Override
	public void run() 
	{
		while(true) 
		{
			buff.get();
			try { Thread.sleep(1000);} catch (InterruptedException e) {}	
		}
	}	
}

public class ThreadSync2 
{
	public static void main(String[] args) 
	{
		Buff buf = new Buff();
		buf.put(0);
		
		Thread p = new Thread(new Produ2(buf));
		Thread c = new Thread(new Consu2(buf));
		
		p.start();
		c.start();		
	}
}
