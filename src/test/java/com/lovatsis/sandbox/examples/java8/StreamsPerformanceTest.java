package com.lovatsis.sandbox.examples.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StreamsPerformanceTest {

    static final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void forLoop() {
        // Old school
        Date start = new Date();
        for (Integer i : list) {
            myFunction(i);
        }
        System.out.println("ForLoop: " + (new Date().getTime() - start.getTime()) / 1000D);
        // Stream
         start = new Date();
        list.stream().forEach(StreamsPerformanceTest::myFunction);
        System.out.println("Stream: " + (new Date().getTime() - start.getTime()) / 1000D);
        // Stream
         start = new Date();
        list.stream().forEach(listEntry -> myFunction(listEntry));
        System.out.println("streamWithLambda: " + (new Date().getTime() - start.getTime()) / 1000D);
        // Parallel stream
         start = new Date();
        list.stream().parallel().forEach(StreamsPerformanceTest::myFunction);
        System.out.println("Stream.parallel: " + (new Date().getTime() - start.getTime()) / 1000D);
        // Parallel stream (lambda expression showcase)
         start = new Date();
        list.stream().parallel().forEach(listEntry -> myFunction(listEntry));
        System.out.println("Stream.parallel with lambda: " + (new Date().getTime() - start.getTime()) / 1000D);

    }


    public static synchronized void myFunction(Integer number) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Something went wrong");
        }
    }
}