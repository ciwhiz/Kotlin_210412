package com.lge.ex15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Predicate<E> {
    boolean test(E e);

    default void foo() {

    }

    static void goo() {

    }
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

    // 익명 객체 vs 람다
    // - 사용하는 만큼의 class 파일이 생성됩니다.
    // - '람다 표현식'을 통해 사용하는 경우, class 파일이 생성되지 않습니다.

    // Java 8 에서는 익명의 인터페이스(한개의 메소드)에 대해서 람다 표현식을 사용할 수 있습니다.
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

        result = filter(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 1;
            }
        });

        //--------------
        result = filter(list, (Integer integer) -> {
            return integer % 2 == 1;
        });
        for (Integer e : result) {
            System.out.println(e);
        }

        result = filter(list, (integer) -> integer % 2 == 1);

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
