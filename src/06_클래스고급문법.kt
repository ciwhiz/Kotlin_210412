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
interface Clickable {
    fun click()

    fun showOff() {

    }
}

// class Button implements Clickable
class Button: Clickable {
    override fun click() {
        println("Button click")
    }
}

fun main() {
    val button = Button()
    button.click()
}


