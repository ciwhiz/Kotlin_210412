package com.lge.ex15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

interface Predicate<E> {
    boolean test(E e);
}

public class Sample {
    static List<Integer> filter(List<Integer> data, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer e : data) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = filter(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        });

        for (Integer e : result) {
            System.out.println(e);
        }
    }
}

/*
public class Sample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        // Immutable - Decorator Pattern
        List<Integer> list2= Collections.unmodifiableList(list);
        list2.add(10);
        list2.add(20);
        list2.add(30);
    }
}
*/
