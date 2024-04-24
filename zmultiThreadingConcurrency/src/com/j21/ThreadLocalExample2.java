package com.j21;

public class ThreadLocalExample2 
{
    // Create a ThreadLocal instance for Integer objects, initializing it with a lambda expression that provides the initial value
    private static ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) throws InterruptedException 
    {
        // Create and start the first thread
        Thread thread1 = new Thread(() -> {
            // Set the ThreadLocal value for this thread to 100
            updateValue(100);
            // Retrieve and print the ThreadLocal value which is unique to Thread 1
            System.out.println("Thread 1: " + threadLocalValue.get());
        });

        // Create and start the second thread
        Thread thread2 = new Thread(() -> {
            // Set the ThreadLocal value for this thread to 200
            updateValue(200);
            // Retrieve and print the ThreadLocal value which is unique to Thread 2
            System.out.println("Thread 2: " + threadLocalValue.get());
        });

        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        thread1.join();
        thread2.join();
    }

    // Helper method to set the value of the thread-local variable
    private static void updateValue(int newValue) {
        threadLocalValue.set(newValue);
    }
}
