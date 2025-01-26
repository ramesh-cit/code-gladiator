package com.concurrency;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThreadExample {

    /**
     * This Java code snippet demonstrates the use of virtual threads to execute multiple tasks concurrently. It creates 10 tasks, each represented by the task(i) method, and executes them using two approaches:
     * <p>
     * Manual virtual thread creation: It creates a list of virtual threads, starts each one, and then waits for all threads to finish using the join() method.
     * ExecutorService with virtual threads: It uses an ExecutorService to manage the execution of tasks, where each task is executed by a virtual thread.
     * In both cases, the task(i) method is executed concurrently for each task, allowing for parallel execution of the tasks.
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int numberOfTasks = 10;

        // Using virtual threads
        List<Thread> virtualThreads = new ArrayList<>();
        IntStream.range(0, numberOfTasks).forEach(i -> {
            Thread vThread = Thread.ofVirtual().unstarted(() -> {
                task(i);
            });
            virtualThreads.add(vThread);
            vThread.start();
        });

        for (Thread thread : virtualThreads) {
            thread.join();
        }

        // Using ExecutorService with virtual threads
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numberOfTasks).forEach(i -> {
                executor.submit(() -> task(i));
            });
        }
    }

    static void task(int taskId) {
        System.out.println("Task " + taskId + " started by " + Thread.currentThread());
        try {
            Thread.sleep(Duration.ofSeconds(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task " + taskId + " ended by " + Thread.currentThread());
    }
}