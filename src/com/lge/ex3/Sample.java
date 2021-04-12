package com.lge.ex3;


public class Sample {
    public static void main(String[] args) {
        String a = "Tom";
        String b = "Suwon";
        int c = 10;
        int d = 1;

        User user = new User.Builder(a)
                .setAddress(b)
                .setAge(c)
                .setLevel(d)
                .build();
    }
}


// 1. Telescoping Constructor Pattern : Java's Idiom
//  => 생성자 오버로딩을 통해 객체를 생성하는 다양한 방법을 제공하자
/*
class User {
    private String name;
    private String address;
    private int age;
    private int level;

    User(String name, String address, int age, int level) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.level = level;
    }

    User(String name, String address, int age) {
        this(name, address, age, 0);
    }

    User(String name, String address) {
        this(name, address, 0, 0);
    }

    User(String name) {
        this(name, "", 0, 0);
    }
}

public class Sample {
    public static void main(String[] args) {
        String a = "Tom";
        String b = "Suwon";
        int c = 10;
        int d = 1;

        // 문제점: 각각의 값이 어떤 속성으로 연결되는지 알기 어렵다.
        User user1 = new User(a);
        User user2 = new User(a, b);
        User user3 = new User(a, b, c);
        User user4 = new User(a, b, c, d);
    }
}
*/