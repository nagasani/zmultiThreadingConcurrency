package com.j21;

import java.util.concurrent.Callable;

class CallSir implements Callable<Integer>
{

	@Override
	public Integer call() throws Exception {
		return 100;
	}
	
}

public class CallableExample
{
	public static void main(String[] args) 
	{
		//Thread t = new Thread(new CallSir());
	}
}
