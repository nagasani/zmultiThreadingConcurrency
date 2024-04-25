package com.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LetsCheckSerialization implements Serializable 
{	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		LetsCheckSerialization letsCheckSerialization1 = new LetsCheckSerialization();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("letsCheckSerialization.ser"));
		out.writeObject(letsCheckSerialization1);
		out.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("letsCheckSerialization.ser"));
		LetsCheckSerialization letsCheckSerialization2 = (LetsCheckSerialization) in.readObject();
        in.close();

        System.out.println(letsCheckSerialization1);
        System.out.println(letsCheckSerialization2);
        System.out.println("Are instances the same? " + (letsCheckSerialization1 == letsCheckSerialization2));
    }
}
