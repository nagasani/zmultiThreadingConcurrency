package com.mul.producer_consumer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerExample1 {

    private static BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        // Create producer and consumer threads
        Thread producer = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer was interrupted");
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer was interrupted");
            }
        });

        // Start both threads
        producer.start();
        consumer.start();
    }

    public static void produce() throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            String item = "Item " + i;
            queue.put(item); // Produce an item
            System.out.println("Produced: " + item);
            Thread.sleep(100); // Simulating time delay
        }
    }

    public static void consume() throws InterruptedException {
        Object item = queue.take(); // Consume an item
        System.out.println("Consumed: " + item);
        Thread.sleep(1000); // Simulating time delay
    }
}
