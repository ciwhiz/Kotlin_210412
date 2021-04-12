package com.lge.ex2;

class User {
    private String name;
    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}


public class Sample {
    static void print() {
    }

    public static void main(String[] args) {
        User user1 = new User("Tom", 42);
        User user2 = user1;

        // 참조 동등성
        if (user1 == user2) {
            System.out.println("동일한 참조값");
        } else {
            System.out.println("동일한 참조값 아닙니다");
        }

        // 객체 동등성
        if (user1.equals(user2)) {
            System.out.println("동일한 속성");
        } else {
            System.out.println("동일한 속성 아닙니다");
        }


        // System.out.println(print());  // Error!
    }
}
