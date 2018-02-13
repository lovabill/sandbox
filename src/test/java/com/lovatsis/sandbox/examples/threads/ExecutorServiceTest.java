package com.lovatsis.sandbox.examples.threads;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    static ExecutorService executor = Executors.newFixedThreadPool(10);

    static String result = null;

    @Test
    public void runnableExecution() throws Exception {

        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                result = "Done";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Future future = executor.submit(runnableTask);

        Assert.assertEquals(null, result); // Job is still running at this moment.

        while (future.get() != null) ;

        Assert.assertEquals("Done", result);

    }

}
