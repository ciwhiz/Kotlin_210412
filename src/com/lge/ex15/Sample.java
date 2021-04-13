package com.lge.ex15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
