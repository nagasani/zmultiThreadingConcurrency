package com.mul.producer_consumer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerExample {

    private static BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        // Create producer thread
        Thread producer = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer was interrupted");
            }
        });

        // Create multiple consumer threads
        Thread consumer1 = new Thread(() -> {
            try {
                while (true) {
                    consume("Consumer 1");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer 1 was interrupted");
            }
        });

        Thread consumer2 = new Thread(() -> {
            try {
                while (true) {
                    consume("Consumer 2");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer 2 was interrupted");
            }
        });

        // Start producer and consumers threads
        producer.start();
        consumer1.start();
        consumer2.start();
    }

    public static void produce() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {  // Increased item count to ensure both consumers get items
            String item = "Item " + i;
            queue.put(item); // Produce an item
            System.out.println("Produced: " + item);
            Thread.sleep(500); // Faster production rate
        }
    }

    public static void consume(String consumerName) throws InterruptedException {
        Object item = queue.take(); // Consume an item
        System.out.println(consumerName + " consumed: " + item);
        Thread.sleep(1000); // Simulating time delay
    }
}
