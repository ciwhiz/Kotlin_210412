package ex12_2

import java.time.LocalDateTime

open class View {
    // open fun click() = println("View click")
}
fun View.click() = println("View click")

class Button : View() {
    // override fun click() = println("Button click")
}
fun Button.click() = println("Button click")

fun main() {
    // Upcasting 허용: 자식의 객체를 부모 타입의 참조를 통해 참조 가능하다
    val view: View = Button()

    // Dynamic Binding: 참조하고 있는 객체의 타입에 기반해서 함수를 호출하는 것
    //--------------
    // 확장 함수는 Static binding 기반으로 동작합니다.
    // 참조 타입을 기반으로 함수를 호출한다.
    view.click()
}


/*
class User {
}

fun User.display() {
    println("User display")
}

fun LocalDateTime.display() {
    println("LocalDateTime display")
}

fun main() {
    val now = LocalDateTime.now()
    now.display()

    val user = User()
    user.display()
}
*/