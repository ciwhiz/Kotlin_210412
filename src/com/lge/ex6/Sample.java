package com.lge.ex6;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

interface State extends Serializable {
}

interface View {
    State getCurrentState();

    void restoreState(State s);
}

class Button implements View {

    // 내부 클래스(Inner class): 일반적으로 사용할 때 주의가 필요합니다.
    //  : 외부 객채에 대한 암묵적인 참조가 내부 필드로 생성합니다.

    // class ButtonState implements State {

    // 중첩 클래스(Nested class)
    // static class ButtonState implements State {
    static class ButtonState implements State {
        // Button outerClassObj;
        String name = "Button";
        int x = 10;
        int y = 20;
    }

    @Override
    public State getCurrentState() {
        return new ButtonState(); // this
    }

    @Override
    public void restoreState(State s) {
        // ...
    }
}

public class Sample {
    public static void main(String[] args) throws Exception {
        Button button = new Button();

        FileOutputStream fos = new FileOutputStream("button.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        State s = button.getCurrentState();
        oos.writeObject(s);
    }
}
/*
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
*/
