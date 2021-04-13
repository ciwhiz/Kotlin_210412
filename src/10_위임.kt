// 10_위임.kt
package ex10

// 위임(Delegation)
// : 하나 이상의 메소드 호출을 다른 객체에게 위임한다.

// 1. 클래스 위임
//  "재사용"
//   1. 상속
//   => 부모의 기능을 암묵적으로 이용할 수 있다.
//   => 상속의 목적은 "다형성"을 구현하기 위해서 사용한다.
/*
interface UIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

open class Rectangle(val x1: Int, val x2: Int, val y1: Int, val y2: Int) : UIElement {
    override fun getHeight(): Int {
        return y2 - y1
    }

    override fun getWidth(): Int {
        return x2 - x1
    }
}

class Panel(x1: Int, x2: Int, y1: Int, y2: Int) : Rectangle(x1, x2, y1, y2)

fun main() {
    val panel = Panel(10, 20, 30, 40)
    println(panel.getWidth())
    println(panel.getHeight())
}
*/


//   2. 포함
//     => 느슨한 결합
//     => 의존성 주입(Dependency Injection)
//        => 실행시간에 의존하는 객체를 다른 객체로 대체하는 것이 가능하다.
//        => 테스트 용이성 좋다.


interface UIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

class Rectangle(val x1: Int, val x2: Int, val y1: Int, val y2: Int) : UIElement {
    override fun getHeight(): Int {
        return y2 - y1
    }

    override fun getWidth(): Int {
        return x2 - x1
    }
}

// 문제점: 위임하는 메소드에 대한 작성이 명시적으로 필요하다.
//    => by

/*
class Panel(private val rectangle: UIElement) : UIElement {
    override fun getHeight(): Int {
        return rectangle.getHeight()
    }

    override fun getWidth(): Int {
        return rectangle.getWidth()
    }
}
*/

// class Panel(private val rectangle: UIElement) : UIElement by rectangle
class Panel(rectangle: UIElement) : UIElement by rectangle

fun main() {
    val panel = Panel(Rectangle(10, 20, 30, 40))
    println(panel.getWidth())
    println(panel.getHeight())
}