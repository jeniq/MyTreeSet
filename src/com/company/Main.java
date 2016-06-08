package com.company;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        MyTreeSet<Integer> treeSet = new MyTreeSet<>();

        treeSet.add(new Integer(10));
        treeSet.add(new Integer(5));
        treeSet.add(new Integer(3));
        treeSet.add(new Integer(6));
        treeSet.add(new Integer(11));
        treeSet.add(new Integer(1));
        treeSet.add(new Integer(2));

        Iterator<Integer> it = treeSet.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
    }
}
