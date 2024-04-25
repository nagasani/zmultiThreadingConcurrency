package com.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SingleTonCloneCheck {

	public static void main(String[] args) 
	{
		SingleTonCloneCheck s = new SingleTonCloneCheck();
		s.serializatoinCheck();
	}

	public void cloneCheck() throws CloneNotSupportedException 
	{
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = singleton1.clone();
		System.out.println(singleton1.hashCode());
		System.out.println(singleton2.hashCode());
		System.out.println("Singleton1 and Singleton2 are the same instance? " + (singleton1 == singleton2));
	}

	public void serializatoinCheck() 
	{
		try 
		{
			Singleton instance1 = Singleton.getInstance();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
			out.writeObject(instance1);
			out.close();

			// Deserialize
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"));
			Singleton instance2 = (Singleton) in.readObject();
			in.close();

			System.out.println(instance1);
			System.out.println(instance2);
			System.out.println("Are instances the same? " + (instance1 == instance2));
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}
