// 06_클래스고급문법2.kt
package ex6_2

import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

interface State : Serializable

// property
interface View {
    var state: State
}

class Button : View {
    // Kotlin
    //  - 기본이 Nested class 입니다.
    //  - inner 키워드를 통해 내부 클래스를 만들 수 있습니다.
    // inner class ButtonState(val name: String) : State

    class ButtonState(val name: String) : State

    override var state: State
        get() {
            return ButtonState("Button")
        }
        set(value) {
            // ...
        }
}

// Kotlin - "Checked Exception"이 존재하지 않습니다.
//  => 예외 처리 구문이 강제되지 않습니다.
//  => 개발자가 예외 처리에 대해서 신경써야 합니다.
fun main() {
    val button = Button()

    val fos = FileOutputStream("button2.dat")
    val oos = ObjectOutputStream(fos)

    val state = button.state
    oos.writeObject(state)
}

