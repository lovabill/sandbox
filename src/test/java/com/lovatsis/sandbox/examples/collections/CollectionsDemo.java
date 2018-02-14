package com.lovatsis.sandbox.examples.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CollectionsDemo {

    @Test
    public void arrayList() throws Exception {
        //Preserves insertion order.
        ArrayList<Integer> arrayList = new ArrayList<>();
        List list = arrayList; //Implements List
        arrayList.addAll(Arrays.asList(2, 1, 3));
        Assert.assertTrue(arrayList.get(0).equals(2));
        Assert.assertEquals(3, arrayList.size());
        //Java8
        arrayList.sort((a, b) -> b.compareTo(a));
        Assert.assertTrue(arrayList.get(0).equals(3));
    }

    @Test
    public void vector() throws Exception {
        //Similar to ArrayList but synchronized and with legacy methods.
        Vector<Integer> vector = new Vector<>();
        List list = vector; //Implements List
        vector.addAll(Arrays.asList(2, 1, 3));
        Assert.assertTrue(vector.firstElement().equals(2));
        Assert.assertEquals(3, vector.size());
        //Java8
        vector.sort((a, b) -> b.compareTo(a));
        Assert.assertTrue(vector.firstElement().equals(3));
    }

    @Test
    public void linkedList() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        List list = linkedList; //Implements List
        linkedList.addAll(Arrays.asList(2, 1, 3));
        Assert.assertTrue(linkedList.getFirst().equals(2));
        Assert.assertEquals(3, linkedList.size());
        //Java8
        linkedList.sort((a, b) -> b.compareTo(a));
        Assert.assertTrue(linkedList.getFirst().equals(3));
        //Manage
        linkedList.removeFirst();
        Assert.assertTrue(linkedList.getLast().equals(1));

        //As FIFO stack
        Queue<Integer> qeueu = linkedList; //Implements Queue
        qeueu.clear();
        qeueu.add(2);
        qeueu.add(1);
        qeueu.add(3);
        Integer fetchedItem = qeueu.poll(); //Fetch the oldest item in queue.
        Assert.assertTrue(fetchedItem.equals(2));
    }

    @Test
    public void priorityQueue() throws Exception {
        //Similar to LinkedList but always ordered.
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(2);
        priorityQueue.add(1);
        priorityQueue.add(3);
        Assert.assertTrue(priorityQueue.peek().equals(1));
        Assert.assertEquals(3, priorityQueue.size());
        //Implements Queue
        Queue queue = priorityQueue;
    }

    @Test
    public void hashSet() throws Exception {
        //Merge duplicates. Permits null. Insertion order is not supported.
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("alpha");
        hashSet.add("alpha");
        hashSet.add("beta");
        Assert.assertTrue(hashSet.size() == 2);
        Assert.assertTrue(hashSet.contains("beta"));

        Set set = hashSet;//Implements Set
    }

    @Test
    public void linkedHashSet() throws Exception {
        //Preserves insertion order. Operations cost O(1).
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("alpha");
        linkedHashSet.add("zeta");
        linkedHashSet.add("beta");
        for (String s : linkedHashSet) {
            Assert.assertTrue(s.equals("alpha"));
            break;
        }

        Set set = linkedHashSet;//Implements Set3
    }

    @Test
    public void treeSet() throws Exception {
        // Keeps order and provides management methods. Operations cost O(log(n)).
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("alpha");
        treeSet.add("zeta");
        treeSet.add("beta");
        Assert.assertTrue(treeSet.first().equals("alpha"));
        Assert.assertTrue(treeSet.size() == 3);

        //Polling
        Assert.assertTrue(treeSet.pollFirst().equals("alpha"));
        Assert.assertTrue(treeSet.size() == 2);

        //Implements Set
        Set set = treeSet;
    }
}
