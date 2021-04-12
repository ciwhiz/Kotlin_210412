package com.lge.ex2;


import java.util.Objects;

// equals를 재정의하면 반드시 hashCode도 재정의해야 합니다.
class User {
    private String name;
    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Object.equals
    @Override
    public boolean equals(Object obj) {
        // 1. 동일한 참조인지 확인한다.
        if (this == obj) {
            return true;
        }

        // 2. null인 체크해야 합니다.
        if (obj == null) {
            return false;
        }

        // 3. User 타입인지 체크한다.
        // if (obj.getClass() != User.class) {
        //    return false;
        // }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;

        // 4. 내부의 속성을 비교한다.
        return age == other.age &&
                name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}


public class Sample {
    static void print() {
    }

    public static void main(String[] args) {
        // User user1 = new User("Tom", 42);
        // User user2 = user1;
        User user1 = null;
        User user2 = new User("Tom", 42);

        // 참조 동등성
        if (user1 == user2) {
            System.out.println("동일한 참조값");
        } else {
            System.out.println("동일한 참조값 아닙니다");
        }

        // 객체 동등성
        // if (user1 != null && user1.equals(user2)) {
        if (Objects.equals(user1, user2)) {
            System.out.println("동일한 속성");
        } else {
            System.out.println("동일한 속성 아닙니다");
        }


        // System.out.println(print());  // Error!
    }
}
