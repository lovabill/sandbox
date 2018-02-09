package com.lovatsis.sandbox.examples.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

//A functional interface is an interface that has just one abstract method, and thus represents a single function contract.
interface FunctionalInterface {
    int operate(int a, int b);
}


public class LambdasShowcaseTest {

    @Test
    public void innerClassInstantiation() {
        //Java7
        FunctionalInterface integerAdderOperator = (new FunctionalInterface() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        });
        Assert.assertEquals(3, integerAdderOperator.operate(1, 2));

        //Java8
        FunctionalInterface integerAdderOperatorLambda = (a, b) -> (a + b);
        Assert.assertEquals(3, integerAdderOperatorLambda.operate(1, 2));
    }

    @Test
    public void comparator() {
        CustomBean customBean1 = new CustomBean(1);
        CustomBean customBean2 = new CustomBean(2);

        //Inner class
        List<CustomBean> collection = Arrays.asList(customBean2, customBean1);
        Collections.sort(collection, new Comparator<CustomBean>() {
            public int compare(CustomBean p1, CustomBean p2) {
                return p1.getValue().compareTo(p2.getValue());
            }
        });
        Assert.assertTrue(collection.get(0).getValue().equals(1));

        //Lambda
        Collections.sort(collection, (p1, p2) -> p1.getValue().compareTo(p2.getValue()));
        Assert.assertTrue(collection.get(0).getValue().equals(1));

        //Java8 Comparator
        Collections.sort(collection, Comparator.comparing(CustomBean::getValue));
        Assert.assertTrue(collection.get(0).getValue().equals(1));
    }

}
