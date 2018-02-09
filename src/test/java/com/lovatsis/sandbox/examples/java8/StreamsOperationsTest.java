package com.lovatsis.sandbox.examples.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsOperationsTest {

    @Test
    public void map() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        System.out.println("Numbers: " + numbers);
        numbers = numbers.stream().map(n -> {
            int inc = 10;
            return n + inc;
        }).collect(Collectors.toList());
        System.out.println("Numbers+Incremenet: " + numbers);

        List<String> wrappedNumbers = numbers.stream().map(n -> "!" + n + "!").collect(Collectors.toList());
        System.out.println("wrappedNumbers: " + wrappedNumbers);

    }

    @Test
    public void filter() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println("Numbers: " + numbers);
        numbers = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println("Odd numbers only: " + numbers);

    }

    @Test
    public void sum() {
        List<Integer> numbers = Arrays.asList(4, 6);
        System.out.println("Numbers: " + numbers);
        int sum = numbers.stream().mapToInt(Integer::new).sum();
        System.out.println("Sum: " + sum);

    }

    @Test
    public void reduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        System.out.println("Numbers: " + numbers);
        Integer sum = numbers.stream().reduce(Integer.valueOf(0), (a, b) -> a + b);
        System.out.println("Sum: " + sum);

    }
}
