package com.language;

public class LambdaExpressionExample {
    public static void main(String[] args) {
        // Lambda expression to implement the run method of the Runnable interface
        Runnable r = () -> System.out.println("Hello, Lambda!");
        r.run();
    }

}
