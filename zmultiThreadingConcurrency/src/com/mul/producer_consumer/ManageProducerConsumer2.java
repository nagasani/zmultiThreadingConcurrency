package com.mul.producer_consumer;

public class ManageProducerConsumer2 {
    public static void main(String[] args) 
    {
    	StringBuffer sb = new StringBuffer();
        Thread producer = new Thread(new Producer(sb));
        Thread consumer = new Thread(new Consumer(sb));

        producer.start();
        consumer.start();
    }
}