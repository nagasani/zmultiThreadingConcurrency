package com.j21;
public class Daemon4 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        thread.start();

        try {
            // Trying to set the daemon flag after the thread has started
            thread.setDaemon(true);
        } catch (IllegalThreadStateException e) {
            System.out.println("Cannot set daemon as the thread is already started");
        }
    }
}
