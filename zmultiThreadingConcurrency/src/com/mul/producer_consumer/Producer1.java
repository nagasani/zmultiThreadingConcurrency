package com.mul.producer_consumer;

public class Producer1 implements Runnable {
    private final Object lock;

    public Producer1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (true) { // Use a loop to continuously produce items
                try {
                    lock.notify(); // Notify any waiting threads
                    lock.wait(); // Wait to be notified
                    System.out.println("Produced at: " + System.currentTimeMillis());
                    Thread.sleep(10000); // Simulate some delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}