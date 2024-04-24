package com.mul.producer_consumer;

public class Producer implements Runnable {

	private StringBuffer sb;

	/**
	 * @param sb
	 */
	public Producer(StringBuffer sb) {
		super();
		this.sb = sb;
	}

	@Override
	public void run() 
	{
		synchronized (sb) 
		{
			while(true) 
			{
				sb.append(System.currentTimeMillis());
				sb.notifyAll();
				try {
				Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
