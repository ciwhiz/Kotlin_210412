// 10_위임2
package ex10_2

import kotlin.reflect.KProperty

// 프로퍼티 위임
//  : 프로퍼티에 대한 getter / setter에 대한 호출을 다른 객체에게 위임한다.

class SampleDelegate {
    operator fun getValue(thisRef: User, property: KProperty<*>): String {
        return "Tom"
    }

    operator fun setValue(thisRef: User, property: KProperty<*>, newValue: String) {
        println("setValue - $newValue")
    }
}

// Type 'SampleDelegate' has no method 'getValue(User, KProperty<*>)'
// and thus it cannot serve as a delegate
// Type 'SampleDelegate' has no method 'setValue(User, KProperty<*>, String)'
// and thus it cannot serve as a delegate for var (read-write property)
class User {
    var name: String by SampleDelegate()
}

fun main() {
    val user = User()
    user.name = "Bob"

    println(user.name)
}