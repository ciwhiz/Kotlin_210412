package com.lge.ex9;

// Java에서 Singleton을 구현하는 방법
// Version 1.
//  문제점: static이기 때문에, Cursor class가 JVM에 로드되는 시점에 초기화가 수행됩니다.
//        Cursor의 초기화에 시간이 걸린다면, 프로그램 구동 시간에 악영향을 미칠 수 있습니다.
//  해결방법
//   : 미리 생성하는 것이 아니라, getInstance()가 처음으로 호출되는 시점에 생성한다.
//    => Lazy Initialization(지연 초기화)
/*
class Cursor {
    // 2. 한개의 공유되는 객체
    private static final Cursor INSTANCE = new Cursor();

    // 1. private 생성자
    private Cursor() {
    }

    // 3. 언제 어디서든 동일한 방법을 통해 접근할 수 있는 메소드
    public static Cursor getInstance() {
        return INSTANCE;
    }
}
*/

/*
class Cursor {
    private static Cursor sInstance;

    private Cursor() {
    }


    // DCLP: Double Checked Locking Pattern
    // => 코드가 선언적이지 않다.
    public static Cursor getInstance() {
        if (sInstance == null) {
            synchronized (Cursor.class) {
                if (sInstance == null)
                    sInstance = new Cursor();
            }
        }

        return sInstance;
    }


    // 한번만 초기화되는 싱글톤의 안전한 생성을 위해서, 매번 동기화를 수행해야 한다.
    /*
    public static Cursor getInstance() {
        synchronized (Cursor.class) {
            if (sInstance == null)
                sInstance = new Cursor();
        }

        return sInstance;
    }


    /*
    // 동시에 getInstance()가 여러개의 스레드에 의해서 수행될 경우 문제가 있습니다.
    public static Cursor getInstance() {
        if (sInstance == null)
            sInstance = new Cursor();

        return sInstance;
    }

}
*/

// Version 3
//  - IODH(Initialization on Demand Holder)
//  1) static final 필드에 대해서는 스레드 안전하게 초기화된다. - JLS
//  2) 중첩 클래스의 static 필드는 클래스 로드 시점이 아닌 첫 접근 시점에 초기화된다. - JLS
class Cursor {
    static class Singleton {
        private static final Cursor INSTANCE = new Cursor();
    }

    private Cursor() {
    }

    public static Cursor getInstance() {
        return Singleton.INSTANCE;
    }
}

public class Sample {
    public static void main(String[] args) {
        Cursor c1 = Cursor.getInstance();
        Cursor c2 = Cursor.getInstance();

        System.out.println(c1);
        System.out.println(c2);
    }
}
