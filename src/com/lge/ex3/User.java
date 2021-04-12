package com.lge.ex3;
// User.java

// Java 에서는 반복적으로 발생하는 보일러플레이트를 없애기 위해서
//  AOP(관점 지향 프로그래밍) 설계를 많이 사용합니다.
//  => Annotation을 통해, 컴파일 타임 또는 런타임에 코드를 생성하는 기술을 많이 사용합니다.
//  => Lombok


// 2. Builder Pattern - GoF's Design Pattern
public class User {
    private String name;
    private String address;
    private int age;
    private int level;

    private User(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.age = builder.age;
        this.level = builder.level;
    }

    public static class Builder {
        private String name; // required
        private String address;
        private int age;
        private int level;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setLevel(int level) {
            this.level = level;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
