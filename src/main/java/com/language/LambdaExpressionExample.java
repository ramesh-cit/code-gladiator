package com.language;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExpressionExample {
    public static void main(String[] args) {
        // Runnable Lambda expression
        String message = "Hello, Lambda!";
        Runnable r = () -> System.out.println(message);
        r.run();
        new Thread(r).start();
        //Comparator Lambda expression
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange");
        strings.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println(strings);
        //Predicate Lambda: Filter a list of integers to get only even numbers using a lambda expression as a Predicate
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0).toList();
        System.out.println(evenNumbers);
        // Function Lambda: Transform a list of strings to uppercase using a lambda expression as a Function.
        List<String> fruits = Arrays.asList("apple", "banana", "kiwi", "orange");
        List<String> uppercaseFruits = fruits.stream().map(String::toUpperCase).toList();
        System.out.println(uppercaseFruits);
        //Consumer Lambda: Print each element of a list using a lambda expression as a Consumer.
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        numbers1.forEach(n -> System.out.println(n));
        numbers1.forEach(System.out::println);
    }

}
