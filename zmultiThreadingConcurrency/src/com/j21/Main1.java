package com.j21;

public class Main1 {
	public static void main(String[] args) 
	{
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					System.out.println("Daemon is running");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("Thread was interrupted");
				}
			}
		});

		// Set the thread as a daemon thread
		thread.setDaemon(true);

		// Start the thread
		thread.start();

		// Main thread sleeps for a while then finishes
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println("Main thread finished");
	}
}
