package com.practice;

import java.io.Serializable;

public class Singleton implements Cloneable, Serializable
{
	private static final long serialVersionUID = 1L;

	private volatile static Singleton instance;
	
	private Singleton() 
	{	
		if (instance != null)
		{
            throw new RuntimeException("Please use getInstance() method for getting an instance,");
        }
		System.out.println("Generated Instance.");
	}
	
	public static Singleton getInstance() 
	{
		//Double Checked Locking
		 if (instance == null) // First check (no locking)
		 {  
            synchronized (Singleton.class) 
            {
                if (instance == null) // Second check (with locking)
                {  
                    instance = new Singleton();
                }
            }
        }
		return instance;		
	}
	
	@Override
    public Singleton clone() throws CloneNotSupportedException
	{ 
        throw new CloneNotSupportedException();
    }
	
	 // this method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Singleton readResolve() 
    {
        return getInstance();
    }
	//Issues here
	//Cloning
	//Serialization
	//Using Class loader
    //Double Locking
    //Reflection
}
