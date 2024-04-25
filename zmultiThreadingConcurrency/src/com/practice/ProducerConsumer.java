package com.practice;
import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumer {
    // Buffer shared by producer and consumer
    private LinkedList<Integer> buffer = new LinkedList<>();
    private int capacity = 10; // maximum number of items in the buffer
    Random rand = new Random();

    class Producer implements Runnable 
    {
        public void run() 
        {
            int value = 0;
            while (true) 
            {
                synchronized (buffer) 
                {
                    // Wait until the buffer has space
                    // Add value to the buffer
                    // Notify the consumer     
                	if(buffer.size() <= capacity) 
                	{
                		buffer.add(rand.nextInt(100));
                		buffer.notify();
                	}
                	else
                	{
                		try { buffer.wait(); } catch (InterruptedException e) {  Thread.currentThread().interrupt(); return; }
                	}
                }
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    class Consumer implements Runnable 
    {
        public void run() 
        {
            while (true) 
            {
                synchronized (buffer) 
                {
                    // Wait until the buffer has data
                    // Consume data from the buffer
                    // Notify the producer
                	if(!buffer.isEmpty()) 
                	{
                		System.out.println(buffer.removeFirst());
                		buffer.notify();
                	}
                	else 
                	{
                		try { buffer.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
                	}
                	
                }
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    public void start() 
    {
        Thread prodThread = new Thread(new Producer());
        Thread consThread = new Thread(new Consumer());
        prodThread.start();
        consThread.start();
    }

    public static void main(String[] args) 
    {
        new ProducerConsumer().start();
    }
}
