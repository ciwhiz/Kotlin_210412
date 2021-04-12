package com.lge.ex6;

interface Clickable {
    void click();

    // Java 8 - default method
    default void showOff() {

    }
}

class Button implements Clickable {
    @Override
    public void click() {
    }
}


public class Sample {
    public static void main(String[] args) {

    }
}
