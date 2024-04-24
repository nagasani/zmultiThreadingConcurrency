package com.mul.producer_consumer;

public class Consumer1 implements Runnable 
{
    private final Object lock;

    public Consumer1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (true) { // Use a loop to continuously consume items
                try {
                    lock.notify(); // Notify any waiting threads
                    lock.wait(); // Wait to be notified
                    System.out.println("Consumed at: " + System.currentTimeMillis());
                    Thread.sleep(10000); // Simulate some delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}