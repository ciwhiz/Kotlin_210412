package com.lge.ex8;

class Point implements Cloneable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

// Java에서 객체 복제를 구현하는 방법 - Object.clone
// 1. Object.clone 오버라이드
// 2. protected -> public
// 3. CloneNotSupportedException 메소드 내부에서 처리
// 4. 캐스트도 메소드 내부에서 처리
// 5. clone을 제공하는 모든 객체는 Cloneable 인터페이스를 구현해야 합니다.
/*
// Mark-up interface: 타입 체크용으로 사용한다.
public interface Cloneable {
}
*/

public class Sample {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        Point p2 = p1.clone();

        System.out.println(p1);
        System.out.println(p2);
    }
}
