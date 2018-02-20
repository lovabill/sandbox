package com.lovatsis.sandbox.examples.exceptions;

import org.junit.Assert;
import org.junit.Test;

public class ExceptionsDemo {


    /**
     * An unchecked exception is thrown during runtime. Runtime exceptions can be handled with a try catch block.
     * The default handling is for the JVM to print the stack trace and then terminate the program.
     */
    @Test(expected = RuntimeException.class)
    public void uncheckedExceptions() {
        Integer a = 2 / 0;
    }

    /**
     * Checked exceptions are used to handle exceptional cases. Compiler must know how to handle them and for that reason
     * methods that throws checked exceptions are declaring their existence on their signature. Exception propagates from
     * method to method (i.e. the call stack) until it hits a try-catch block that can handle it.
     *
     * @throws ClusterNodeException
     */
    @Test(expected = ClusterNodeException.class)
    public void checkedExceptions() throws ClusterNodeException {
        throw new ClusterNodeException();
    }


    /**
     * All exceptions can be handled. Checked exceptions must be handled.
     */
    @Test
    public void catchExceptions() {
        Integer a = null;
        try {
            a = 2 / 0;
        } catch (RuntimeException e) {
            /* An error can be swallowed and continue with the flow normally, or can be rethrown
               in the same or new form (e.g. a runtime error can be transformed into a checked exception
               and handled accordingly).
            */
            a = 1;
        } catch (Exception e) {
            //Only one catch block is executed. Make sure the most generic exception is caught last.
            a = 2;
        } finally {
            /* Executed on both cases: when exception is thrown and when not*/
            if (a == 1) {
                a = 10;
            }
        }
        Assert.assertTrue(a.equals(10));

        try {
            throw new ClusterNodeException();
        } catch (ClusterNodeException e) {
            //Handling of the exceptional case
            if (e instanceof ClusterNodeException) {
                System.out.println("Skip node. Use next one.");
            } else {
                Assert.fail();
            }
        }
    }

    /**
     * Errors should be thrown when something goes irreversibly wrong and should not be handled.
     */
    @Test(expected = Error.class)
    public void errors() {
        throw new Error();
    }

    /**
     * Errors are not exceptions. They subclass the Throwable class which can be handled in a try-class block as well.
     * However, handling errors is not recommended.
     */
    @Test
    public void catchErrors() {
        try {
            throw new Error();
        } catch (Throwable e) {

        }
    }
}
