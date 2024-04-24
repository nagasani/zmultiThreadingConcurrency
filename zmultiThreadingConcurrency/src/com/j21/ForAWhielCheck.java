package com.j21;

public class ForAWhielCheck {
	
	
	public static void main(String[] args) {
		
		int i =0;
		
		while(true) 
		{
			System.out.println(i);
			i++;
			if(i > 1000000) 
			{
				break;
			}
		}
	}

}
