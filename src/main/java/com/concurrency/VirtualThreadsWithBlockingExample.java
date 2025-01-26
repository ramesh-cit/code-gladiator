package com.concurrency;

import java.util.stream.IntStream;

/**
 * In the context of **virtual threads** in Java, the term "continuation" refers to the internal mechanism that virtual threads use to **pause and resume their execution**. This is at the heart of how virtual threads allow concurrency while remaining lightweight and efficiently managed by the JVM.
 * Virtual threads use **continuations** internally to "suspend" the execution mid-way (e.g., during a blocking operation such as I/O or sleep) and resume it later without occupying or blocking physical operating system threads.
 * Although the `Continuation` class itself was used during the experimental stages of **Project Loom**, it is not directly exposed to developers as part of the standard API for managing virtual threads. Instead, virtual threads abstract these details, making it easy for you to work with them in a straightforward manner. The **continuation mechanism** works behind the scenes.
 *
 */
public class VirtualThreadsWithBlockingExample {
    public static void main(String[] args) {
        // Create and start multiple virtual threads
        IntStream.range(1, 5).forEach(taskId -> {
            Thread.ofVirtual().start(() -> {
                System.out.println("Start Task " + taskId + " in Virtual Thread: " + Thread.currentThread());

                // Simulate blocking work (e.g., using sleep)
                try {
                    Thread.sleep(2000); // Simulating a blocking operation
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Task completion
                System.out.println("End Task " + taskId + " in Virtual Thread: " + Thread.currentThread());
            });
        });

        // Keep the main thread alive for tasks to complete
        try {
            Thread.sleep(3000); // Sleep the main thread for demonstration purposes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}