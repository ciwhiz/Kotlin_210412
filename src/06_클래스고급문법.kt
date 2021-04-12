// 06_클래스고급문법.kt
package ex6

// Kotlin -> Java 1.6
//           Java 8

// Interface
// 1. 인터페이스는 구현을 제공하지 않습니다.
//    문제점: '인터페이스' 에 새로운 기능을 추가하는 것이 어렵습니다.
//    => 인터페이스에 기본 구현을 제공하는 것은 유용하다.
//    => Java 8 부터 인터페이스에 기본 구현을 제공할 수 있습니다.
//       default method / defender method

// 2. 여러 개의 인터페이스를 구현할 때, 구현의 충돌이 발생할 수 있습니다.
interface Clickable {
    // open fun
    fun click()

    // open fun
    fun showOff() {
        println("Clickable")
    }
}

interface Focusable {
    fun focus()

    fun showOff() {
        println("Focusable")
    }
}

// class Button implements Clickable implements Focusable


// open: 상속을 허용하는 키워드
// open class Button : Clickable, Focusable {
abstract class Button : Clickable, Focusable {
    override fun showOff() {
        super<Clickable>.showOff()  // Clickable.showOff
        super<Focusable>.showOff()  // Focusable.showOff
    }

    override fun focus() {
    }

    override fun click() {
        println("Button click")
    }

    open fun move(x: Int, y: Int) {
        println("Move - $x,$y")
    }

    // open: Soft Keyword
    // - open class
    // - open fun

    // Java: final void open() {}
    fun open() {

    }

    // open fun
    abstract fun display()

}

// 상속의 문제점
// 1. 기반 클래스를 변경할 경우, 하위 클래스의 동작이 예기치 않게 변경될 수 있습니다.
// 2. 상속을 위한 설계와 문서를 갖추거나, 그럴 수 없다면 상속을 금지해라 - Effective Java

// 상속
// 1. 코틀린의 기본 클래스는 상속이 금지되어 있습니다.
// Java: final class Button {}
// Kotlin: class Button
// - open: 상속 허용

// 2. 코틀린의 기본 메소드는 오버라이딩이 금지되어 있습니다.
// - open: 오버라이딩 허용

// class MouseButton extends Button
class MouseButton : Button() {
    override fun move(x: Int, y: Int) {
        println("Mouse Move - $x,$y")
    }

    override fun display() {
    }
}


fun main() {
    val button: Button = MouseButton()
    button.click()
    button.showOff()

    button.move(10, 20)
}


