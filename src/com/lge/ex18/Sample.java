package com.lge.ex18;

import java.util.ArrayList;

public class Sample {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("hello");
        arr.add("ok");
        // arr.add(42);
        // 바이트코드 안에서 제네릭 타입에 대한 정보는 존재하지 않습니다.

        ArrayList arr2 = new ArrayList();
        arr2.add("hello");
        arr2.add("ok");

    }
}
