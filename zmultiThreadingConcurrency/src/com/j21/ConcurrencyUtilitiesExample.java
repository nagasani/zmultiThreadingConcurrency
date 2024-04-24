package com.j21;
import java.util.concurrent.*;

public class ConcurrencyUtilitiesExample 
{
    // A Semaphore with 2 permits, simulating a limited resource pool.
    private static final Semaphore semaphore = new Semaphore(2);

    // A CountDownLatch initialized with a count of 1, to make sure task starts after preparation is done.
    private static final CountDownLatch startSignal = new CountDownLatch(1);

    // A CyclicBarrier for 3 threads, with a barrier action that announces all parties have reached.
    private static final CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All parties are at the barrier. Let's proceed!"));

    public static void main(String[] args) throws InterruptedException 
    {
        // Executor service to manage threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit three tasks to the executor
        for (int i = 0; i < 3; i++) {
            int taskId = i;
            executor.submit(() -> {
                try {
                    prepare(taskId);
                    startSignal.await();  // Wait until the latch has counted down to zero
                    useResource(taskId);  // Access resource controlled by the semaphore
                    barrier.await();      // Wait for all parties to reach the barrier
                    finish(taskId);
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Simulate preparation is done and count down the latch
        Thread.sleep(1000); // Simulate time for preparation
        startSignal.countDown();
        System.out.println("Start signal given");

        // Shut down executor service
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static void prepare(int taskId) throws InterruptedException 
    {
        System.out.println("Task " + taskId + ": Preparing");
        Thread.sleep((long) (Math.random() * 1000)); // Simulate time to prepare
    }

    private static void useResource(int taskId) {
        try {
            semaphore.acquire(); // Acquire a permit from the semaphore
            System.out.println("Task " + taskId + ": Using the resource");
            Thread.sleep((long) (Math.random() * 2000)); // Simulate usage time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(); // Release the permit
            System.out.println("Task " + taskId + ": Released the resource");
        }
    }

    private static void finish(int taskId) {
        System.out.println("Task " + taskId + ": Finished work and passed the barrier");
    }
}
