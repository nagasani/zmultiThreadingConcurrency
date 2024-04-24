package com.j21;

import java.util.concurrent.*;

public class FactorialCalculator implements Callable<Long> 
{
    private final int number;

    // Constructor to initialize the factorial calculator with a specific number
    public FactorialCalculator(int number) {
        this.number = number;
    }

    @Override
    // The call method is where the task's execution logic goes. It returns a result and can throw exceptions.
    public Long call() throws Exception 
    {
        long result = 1;
        if (number < 0) 
        {
            // Check for invalid input
            throw new IllegalArgumentException("Number must be non-negative.");
        }
        // Loop to calculate factorial
        for (int i = 2; i <= number; i++) 
        {
            result *= i;
            // Simulate delay to mimic a long-running process
            TimeUnit.MILLISECONDS.sleep(100);
        }
        return result;  // Return the computed factorial
    }

    public static void main(String[] args) 
    {
        // Create an executor service with a single thread to run the callable task
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        // Submit the callable task to the executor and obtain the Future object
        Future<Long> futureResult = executor.submit(new FactorialCalculator(5));  // Calculating factorial of 5

        try 
        {
            // Use future.get() to retrieve the result of the callable task. This method blocks until the result is available.
            Long result = futureResult.get();  // This call blocks until the task completes
            System.out.println("Factorial is: " + result);
        } 
        catch (InterruptedException e) 
        {
            // Handle interruptions to the thread execution
            Thread.currentThread().interrupt();
            System.out.println("Task was interrupted.");
        } catch (ExecutionException e) {
            // Handle exceptions thrown during the task execution
            System.out.println("Error occurred during execution: " + e.getCause());
        }

        // Shutdown the executor to free up system resources and terminate the application properly
        executor.shutdown();
        try {
            // Wait for a specified time for all tasks to terminate
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                // Force shutdown if tasks are still running after waiting
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            // Handle interruptions during shutdown
            executor.shutdownNow();
        }
    }
}
