package com.practice;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class LetsCheckClassLoader {
	
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, MalformedURLException 
	{
		
		Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = null;

        try {
            Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);  // Make the private constructor accessible
            instance2 = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Instance 1 hash: " + instance1.hashCode());
        System.out.println("Instance 2 hash: " + instance2.hashCode());
        System.out.println("Are both instances the same? " + (instance1 == instance2));
		
	}

}
