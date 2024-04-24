package com.mul.producer_consumer;

public class ManageProducerConsumer {
    public static void main(String[] args) {
        Object lock = new Object();

        Thread producer1 = new Thread(new Producer1(lock));
        Thread consumer1 = new Thread(new Consumer1(lock));

        producer1.start();
        consumer1.start();
    }
}