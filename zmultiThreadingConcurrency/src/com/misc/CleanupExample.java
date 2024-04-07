package com.misc;

public class CleanupExample 
{
	@Override
	protected void finalize() throws Throwable 
	{
		try  
		{
			// Cleanup code here
			System.out.println("Cleanup resources");
		} 
		finally 
		{
			super.finalize();
		}
	}

	public static void main(String[] args) 
	{
		CleanupExample example = new CleanupExample();
		example = null;

		// Suggest garbage collection
		System.gc();
		System.out.println("Garbage Collection requested");
		
		try 
		{
			Thread.sleep(11110);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
