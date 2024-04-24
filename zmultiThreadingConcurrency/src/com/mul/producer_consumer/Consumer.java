package com.mul.producer_consumer;

public class Consumer implements Runnable {

	private StringBuffer sb;

	/**
	 * @param sb
	 */
	public Consumer(StringBuffer sb) {
		super();
		this.sb = sb;
	}

	@Override
	public void run() {
		
		synchronized (sb) 
		{
			System.out.println(sb.toString());
			sb.notifyAll();
			try 
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
